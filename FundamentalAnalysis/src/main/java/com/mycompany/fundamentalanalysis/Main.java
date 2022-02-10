/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import com.mycompany.fundamentalanalysis.gui.MainJFrame;
import com.mycompany.fundamentalanalysis.gui.ProgressBar;

import java.nio.file.Paths;

import java.io.File;

import java.util.ArrayList;

import javax.swing.JOptionPane;

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
        Configuration conf = new Configuration(Paths.get(System.getProperty("user.home")
                + File.separator + "Documents" + File.separator 
                + "FundamentalAnalysisConfiguration"));
        try {
            conf.createApplicationDataFolder();
        } catch (Exception e) {
            ErrorReport error = new ErrorReport("Nie udało się utworzyć katalogu z konfiguracją.");
            error.showErrorInFrame();
            System.exit(0);
        }
        
        //Step 2 - Get list of companies from configuration
        XMLStorage storagePoint = new XMLStorage (conf.getConfigurationDirectoryPath() + "");
        //create new list in configuration if not exist
        try {
            boolean b = conf.createPreliminaryListOfCompanies(storagePoint);
            //information for user
            if(b == true) {
                JOptionPane.showMessageDialog(null, "Pierwsze uruchomienie programu, "
                        + "stworzono domyślną listę spółek i indeksów", 
                        "Informacja", JOptionPane.INFORMATION_MESSAGE);
            }
        } catch (Exception e){
            ErrorReport error = new ErrorReport("Domyślna lista nie istnieje, "
                    + "próba jest utworzenia nie powiodła się."
                    + " Utwórz listę ręcznie w katalogu konfiguracyjnym.");
            error.showErrorInFrame();
            try {
                error.setErrorMessage(e.toString());
                error.saveInFile(conf.getConfigurationDirectoryPath());
            } catch (Exception e2){}
            
            System.exit(0);
        }
        //Collect from configuration file
        ArrayList<Company> companies = new ArrayList<Company>();
        try {
            companies.addAll(storagePoint.getAllCompanies());
        } catch (Exception e) {
            ErrorReport error = new ErrorReport("Nie udało się pobrać listy "
                    + "firm z pliku konfiguracyjnego.");
            error.showErrorInFrame();
            try {
                error.setErrorMessage(e.toString());
                error.saveInFile(conf.getConfigurationDirectoryPath());
            } catch (Exception e2){}
            System.exit(0);
        }
        
        //Step 3 - Collect data from internet service and set data to Company collection
        ProgressBar progBar = new ProgressBar();
        progBar.setVisible(true);
        progBar.setMaximum(companies.size());
        
        for (int i = 0; i < companies.size(); i++) {
            //progress bar set progress
            progBar.setPercents(i);
            //collection of data
            try {
                DataCollector dc = new DataCollector(companies.get(i));
                dc.collectData();
                //provide data to companies
                companies.get(i).setCurrentPE(dc.getCurrentPE());
                companies.get(i).setCurrentPOE(dc.getCurrentPOE());
                companies.get(i).setHistoricalPE(dc.getHistoricalPE());
                companies.get(i).setHistoricalPOE(dc.getHistoricalPOE());
            } catch (Exception e) {
                ErrorReport error = new ErrorReport("Nie udało się pobrać danych "
                        + "ze źródła zewnętrznego.");
                progBar.closeProgressBar();
                error.showErrorInFrame();
                try {
                    error.setErrorMessage(e.toString());
                    error.saveInFile(conf.getConfigurationDirectoryPath());
                } catch (Exception e2){}
                System.exit(0);
            }
        }
        
        progBar.closeProgressBar();
        
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
}