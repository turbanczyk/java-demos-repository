/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.mycompany.fundamentalanalysis.gui.MainJFrame;
import com.mycompany.fundamentalanalysis.gui.ProgressBar;

import java.io.File;
import java.io.IOException;

import java.nio.file.Paths;
import java.nio.file.Path;

import javax.swing.JOptionPane;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

/**
 * Main is the class where application start. 
 *  
 * @author tomeku
 */
public class Main {
    
    /**
     * The method used to start application.
     * @param args Parameter defines the parameters with which the application 
     * can be launched.
     */
    public static void main(String[] args) {
        
        //Step 1 - Set configuration directory
        Configuration conf = new Configuration(Paths.get(""));
        try {
            conf = setConfiguration();
        } catch (IOException e) {
            reportError("Nie udało się utworzyć katalogu z konfiguracją.", conf.getConfigurationDirectoryPath());
        }
        
        //Step 2 - Get list of companies from configuration
        ArrayList<Company> companies = new ArrayList<Company>();
        try {
            companies = gatherListOfCompanies(conf);
        } catch (Exception e) {
            reportError("Nie udało się pobrać listy firm z pliku konfiguracyjnego.", 
                    conf.getConfigurationDirectoryPath());
        } 
        
        //Step 3 - Collect data from internet service and set data to Company collection
        try {
            companies = collectData(companies);
        } catch (IOException e) {
            reportError("Nie udało się pobrać danych ze źródła zewnętrznego.", 
                    conf.getConfigurationDirectoryPath());
        }
        
        //Step 4 - build indexes
        Index wig20 = new Index().buildIndex(companies, "WIG20");
        Index mwig40 = new Index().buildIndex(companies, "mWIG40");
        Index swig80 = new Index().buildIndex(companies, "sWIG80");
        
        //Step 5 - start graphical user interface
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        //Create and display the form
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                //new MainJFrame().setVisible(true);
                MainJFrame frame = new MainJFrame(wig20, mwig40, swig80);
                frame.setVisible(true);
            }
        });
    }
    
    private static void reportError(String errorMessage, Path applicationDirectory) {
        ErrorReport error = new ErrorReport(errorMessage);
        error.showErrorInFrame();
        System.exit(0);
    }
    
    private static Configuration setConfiguration() throws IOException {
        Configuration conf = new Configuration(Paths.get(System.getProperty("user.home")
                + File.separator + "Documents" + File.separator 
                + "FundamentalAnalysisConfiguration"));
        
        conf.createApplicationDataFolder();
        
        return conf;
    }
    
    private static ArrayList<Company> gatherListOfCompanies(Configuration configurationObject) 
            throws ParserConfigurationException, SAXException , IOException, TransformerException {
        XMLStorage storagePoint = new XMLStorage (configurationObject.getConfigurationDirectoryPath() + "");
        
        //create new list in configuration if not exist
        boolean b = configurationObject.createPreliminaryListOfCompanies(storagePoint);
        //information for user
        if(b == true) {
            JOptionPane.showMessageDialog(null, "Pierwsze uruchomienie programu, "
                    + "stworzono domyślną listę spółek i indeksów", 
                    "Informacja", JOptionPane.INFORMATION_MESSAGE);
        }
   
        return storagePoint.getAllCompanies();
    }
    
    private static ArrayList<Company> collectData(ArrayList<Company> listOfCompanies) throws IOException {
        ProgressBar progBar = new ProgressBar();
        progBar.setVisible(true);
        progBar.setMaximum(listOfCompanies.size());
        
        for (int i = 0; i < listOfCompanies.size(); i++) {
            //progress bar set progress
            progBar.setPercents(i);
            
            //collection of data
            DataCollector dc = new DataCollector(listOfCompanies.get(i));
            dc.collectData();
            
            //provide data to companies
            listOfCompanies.get(i).setCurrentPE(dc.getCurrentPE());
            listOfCompanies.get(i).setCurrentPOE(dc.getCurrentPOE());
            listOfCompanies.get(i).setHistoricalPE(dc.getHistoricalPE());
            listOfCompanies.get(i).setHistoricalPOE(dc.getHistoricalPOE());
        }
        
        progBar.closeProgressBar();
        
        return listOfCompanies;
    }
}