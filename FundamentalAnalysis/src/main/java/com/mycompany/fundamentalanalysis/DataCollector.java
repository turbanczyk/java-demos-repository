/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import lombok.Getter;
import lombok.Setter;

/**
 * DataCollector is the class which allow collect data from external source
 * (internet service), proceed them and return in form proper for use in application.
 * 
 *  
 * @author tomeku
 */
@Getter
@Setter
public class DataCollector {
    
    private Company company;
    private String currentIndicatorsString;
    private String historicalIndicatorsString;
    private final String html = "https://www.biznesradar.pl/";
    
    /**
     * Constructor with one parameter.
     * @param company Parameter from class Company for which data will be collected
     * and in which collected data will
     * be storage.
     */
    public DataCollector (Company company) {
        Preconditions.checkArgument(company != null, 
                "Company can't be null");
        this.company = company;
    }
    
    /**
     * The metod used to collect data from external source (internet service), start
     * this method is necessary to collect data.
     * @throws Exception Throw exception in situation when gatering of information
     * will be not possible.
     */
    public void collectData () throws Exception {
        currentIndicatorsString = collectCurrentIndicators();
        historicalIndicatorsString = collectHistoricalIndicators();
    }
    
    /**
     * The method used to obtain current P/E indicator value, for proper use first
     * necessary is to start collectData() method
     * @return Return current value of P/E indicator. 
     */
    public double getCurrentPE () {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(currentIndicatorsString), 
                "currentIndicatorString can't be empty or null");
        
        Pattern decimalNumPattern = Pattern.compile("C/Z.-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(currentIndicatorsString);
        
        String rawDouble = "0";
        if (matcher.find(0)) {
            rawDouble = matcher.group();
            rawDouble = rawDouble.replaceAll("C/Z ", "");
        }
        
        return Double.parseDouble(rawDouble);
    }
    
    /**
     * The method used to obtain current P/OE indicator value, for proper use first
     * necessary is to start collectData() method
     * @return Return current value of P/OE indicator. 
     */
    public double getCurrentPOE () {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(currentIndicatorsString), 
                "currentIndicatorString can't be empty or null");
        
        Pattern decimalNumPattern = Pattern.compile("C/ZO.-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(currentIndicatorsString);
        
        String rawDouble = "0";
        if (matcher.find(0)) {
            rawDouble = matcher.group();
            rawDouble = rawDouble.replaceAll("C/ZO ", "");
        } else {
            return 0;
        }
        
        return Double.parseDouble(rawDouble);
    }
    
    /**
     * The method used to obtain historical value of P/E indicator, for proper use first
     * necessary is to start collectData() method
     * @return Return historical value of P/E indicator. 
     */
    public double [] getHistoricalPE () {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(historicalIndicatorsString), 
                "historicalIndicatorsString can't be empty or null");
        
        Pattern decimalNumPattern = Pattern.compile("Cena \\/ Zysk .* Zysk operacyjny na akcję");
        Matcher matcher = decimalNumPattern.matcher(historicalIndicatorsString);
        
        String rawDouble = "0";
        if (matcher.find(0)) {
            rawDouble = matcher.group();

            rawDouble = rawDouble.replaceAll("Cena / Zysk ", "");
            rawDouble = rawDouble.replaceAll("Zysk operacyjny na akcję", "");
            
            rawDouble = rawDouble.replaceAll("~sektor\s-?\\+?[0-9]*.{1}[0-9]*%?", "");
            rawDouble = rawDouble.replaceAll("(k/k)?(r/r)?\s\\+?-?[0-9]*.[0-9]*%", "");
        } else {
            return new double []{0};
        }
        List<String> doublesInString = findDecimalNums (rawDouble);
        double [] table = new double [doublesInString.size()];
        for (int i = 0; i < table.length; i++) {
            table [i] = Double.parseDouble(doublesInString.get(i));
        }
        
        return table;
    }
    
    /**
     * The method used to obtain historical value of P/OE indicator, for proper use first
     * necessary is to start collectData() method
     * @return Return historical value of P/OE indicator. 
     */
    public double [] getHistoricalPOE () {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(historicalIndicatorsString), 
                "historicalIndicatorsString can't be empty or null");
        
        Pattern decimalNumPattern = Pattern.compile("Cena \\/ Zysk operacyjny.* Enterprise Value na akcję");
        Matcher matcher = decimalNumPattern.matcher(historicalIndicatorsString);
        
        String rawDouble = "0";
        if (matcher.find(0)) {
            rawDouble = matcher.group();

            rawDouble = rawDouble.replaceAll("Cena / Zysk operacyjny ", "");
            rawDouble = rawDouble.replaceAll("Enterprise Value na akcję", "");
            
            rawDouble = rawDouble.replaceAll("~sektor\s-?\\+?[0-9]*.{1}[0-9]*%?", "");
            rawDouble = rawDouble.replaceAll("(k/k)?(r/r)?\s\\+?-?[0-9]*.[0-9]*%", "");
        } else {
            return new double []{0};
        }
        List<String> doublesInString = findDecimalNums (rawDouble);
        double [] table = new double [doublesInString.size()];
        for (int i = 0; i < table.length; i++) {
            table [i] = Double.parseDouble(doublesInString.get(i));
        }
        
        return table;
    }
    
    /**
     * The method used to collect from external source (internet service) current
     * indicators
     * @return Return value of current indicators (and other information) in form 
     * of string which must be proceed in another methods.
     * @throws Exception Throw exception in situation when gatering of information
     * will be not possible.
     */
    private String collectCurrentIndicators () throws Exception {
        Connection connect = Jsoup.connect(html+"notowania/"+company.getCompanyId());
        Document doc = connect.get();
        Elements htmlDiv = doc.select("div.element.ratios");
        return htmlDiv.text();
    }
    
    /**
     * The method used to collect from external source (internet service) historical
     * indicators
     * @return Return value of historical indicators (and other information) in form 
     * of string which must be proceed in another methods.
     * @throws Exception Throw exception in situation when gatering of information
     * will be not possible.
     */
    private String collectHistoricalIndicators () throws Exception {
        Connection connect = Jsoup.connect(html+"wskazniki-wartosci-rynkowej/"+company.getCompanyId());
        Document doc = connect.get();
        Elements htmlDiv = doc.select("table.report-table");
        
        return htmlDiv.text();
    }
    
    /**
     * The method used to obtain string in double number format from string.
     * @param stringToSearch Parameter string which should be proceeding.
     * @return Return List of object String in the format od double number.
     */
    private List<String> findDecimalNums(String stringToSearch) {
        Pattern decimalNumPattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        Matcher matcher = decimalNumPattern.matcher(stringToSearch);

        List<String> decimalNumList = new ArrayList<>();
        while (matcher.find()) {
            decimalNumList.add(matcher.group());
        }
        return decimalNumList;
    }
}
