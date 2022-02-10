/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import lombok.Getter;
import lombok.Setter;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * Company is the class which allow an application to 
 * agregate information about single company.
 * A Company object encapsulate main information about company like
 * company id, company name, index do which company is selected, current
 * values of main indicators.
 * 
 * Company object is also responsible for calculate average historical data
 * for main indicators.
 *  
 * @author tomeku
 */
@Getter
@Setter
public class Company {
    
    private String companyId;
    private String companyName;
    private String index;
    
    private double currentPE;
    private double currentPOE;
    private double [] historicalPE;
    private double [] historicalPOE;
    
    /**
     * Class constructor with three parameters. 
     * This constructor doesn't initial indicators, which should be indicated
     * by set methods (current P/E, current P/OE, histrical P/E, historical P/OE.
     * @param companyId Parameter is unique company id, allow to correct 
     * identify company
     * @param companyName Parameter set company name
     * @param index Parameter determines to which index the company belongs on
     */
    public Company (String companyId, String companyName, String index) {
        Preconditions.checkArgument(!Strings.isNullOrEmpty(companyId), 
                "Company Id can't be empty or null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(companyName), 
                "Company name can't be empty or null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(index), 
                "Index can't be null.");
        Preconditions.checkArgument(index.equals("WIG20") || index.equals("mWIG40") 
                || index.equals("sWIG80"), 
                "Index can be only: WIG20 or mWIG40 or sWIG80.");
        
        this.companyId = companyId;
        this.companyName = companyName;
        this.index = index;
    }
    
    /**
     * The metod used to calculate the historical average value of price/earnings
     * indicator.
     * To use this method first should be set value of table historical 
     * price/earnings 
     * @return Return average historical value of price/earnings indicator
     */
    public double calculateHistoricalAveragePE () {
        Preconditions.checkArgument(historicalPE != null, 
                "To calculate historical average PE please first set table historicalPE");
        
        double average = 0;
        for (int i =0; i<historicalPE.length; i++){
            average += historicalPE[i];
        }
        average = average / historicalPE.length;
        return roundDouble(average);
    }
    
    /**
     * The method is used to calculate the historical average value of 
     * price/operational earnings
     * indicator.
     * To use this method first should be set value of table historical 
     * price/operational earnings 
     * @return Return average historical value of price/operational earnings indicator
     */
    public double calculateHistoricalAveragePOE () {
        Preconditions.checkArgument(historicalPOE != null, 
                "To calculate historical average POE please first set table historicalPOE");
        
        double average = 0;
        for (int i =0; i<historicalPOE.length; i++){
            average += historicalPOE[i];
        }
        average = average / historicalPOE.length;
        return roundDouble(average);
    }
    
    /**
     * The method is used to calculate deviation from average value.
     * @param currentValue Parameter of current value.
     * @param averageValue Parameter of historical value.
     * @return Return deviation current value from average value.
     */
    public static double percentageDeviationFromAverage(double currentValue, double averageValue){
        
        double deviation = 0;
        if(currentValue != 0){
            deviation = (averageValue/currentValue)-1;
        }
        //multiply by 100 to return %
        deviation = roundDouble(deviation*100);
        return deviation;
    }
    
    /**
     * The metod used to round the double number to two decimal places
     * @param value Number double type.
     * @return Number double type, rounded to two decimal places.
     */
    private static double roundDouble(double value){
        int places = 2;
        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
    
}
