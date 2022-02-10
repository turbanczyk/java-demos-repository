/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.nio.file.Paths;
import org.junit.jupiter.api.Assertions;
import java.io.File;

/**
 *
 * @author tomeku
 */
public class DataCollectorTest {
    
    private DataCollector tauron;
    private DataCollector cdprojekt;
            
    public DataCollectorTest() {
        tauron = new DataCollector(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        cdprojekt = new DataCollector(new Company("CD-PROJEKT", "CD Projekt S.A.", "WIG20"));
        
        //set prelimiary data 
        String testDirectory = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator
                +"java"+File.separator+"com"+File.separator+"mycompany"
                +File.separator+"fundamentalanalysis"+File.separator+"testData";
        
        try {
        BufferedReader reader = new BufferedReader(new FileReader(testDirectory+
                File.separator+"currentIndicatorsStringCD-PROJEKT"));
        String currentLine = reader.readLine();
        reader.close();
        cdprojekt.setCurrentIndicatorsString(currentLine);
        
        
        reader = new BufferedReader(new FileReader(testDirectory+File.separator+"currentIndicatorsStringTAURON"));
        currentLine = reader.readLine();
        reader.close();
        tauron.setCurrentIndicatorsString(currentLine);
        
        
        reader = new BufferedReader(new FileReader(testDirectory+File.separator+"historicalIndicatorsStringCD-PROJEKT"));
        currentLine = reader.readLine();
        reader.close();
        cdprojekt.setHistoricalIndicatorsString(currentLine);
        
        reader = new BufferedReader(new FileReader(testDirectory+File.separator+"historicalIndicatorsTAURON"));
        currentLine = reader.readLine();
        reader.close();
        tauron.setHistoricalIndicatorsString(currentLine);
        
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    @Test
    public void testConstructor() {
        System.out.println("testConstructor");
        
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           DataCollector t = new DataCollector(null);
        });
    }
    
    /**
     * Test of collectData method, of class DataCollector.
     */
    @Test
    public void testCollectData() throws Exception {
        DataCollector dc = new DataCollector (new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
        dc.collectData();
        
        //check that some data was collected
        assertThat(dc.getCurrentPE()).isNotNull();
        assertThat(dc.getHistoricalPE()).isNotNull();
        
        //check that table with historical PE contains some numbers
        double [] tabTauron = {13.41, 11.59, 10.67, 7.76, 7.53, 7.13, 5.74, 
            5.65, 5.68, 4.58, 5.17, 5.95, 5.85, 8.03, 7.62, 8.23, 7.49, 6.01, 
            6.57, 4.76, 13.59, 8.69, 4.59, 5.11, 3.87, 3.09, 4.25, 2.98, 18.73, 
            38.17, 9.88, 13.14};
        
        for (int i =0; i < tabTauron.length; i++) {
            assertThat(dc.getHistoricalPE()).contains(tabTauron[i]);
        }
    }

    /**
     * Test of getCurrentPE method, of class DataCollector.
     */
    @Test
    public void testGetCurrentPE() {
        System.out.println("getCurrentPE");
        
        //test of use without first used collectData() method
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           DataCollector t = new DataCollector(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
           t.getCurrentPE();
        });
        
        //next test
        assertThat(tauron.getCurrentPE()).isEqualTo(0);
        assertThat(cdprojekt.getCurrentPE()).isEqualTo(16.21);
    }

    /**
     * Test of getCurrentPOE method, of class DataCollector.
     */
    @Test
    public void testGetCurrentPOE() {
        System.out.println("getCurrentPOE");
        
        //test of use without first used collectData() method
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           DataCollector t = new DataCollector(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
           t.getCurrentPOE();
        });
        
        //next test
        
        assertThat(tauron.getCurrentPOE()).isEqualTo(0);
        assertThat(cdprojekt.getCurrentPOE()).isEqualTo(16.13);
    }

    /**
     * Test of getHistoricalPE method, of class DataCollector.
     */
    @Test
    public void testGetHistoricalPE() {
        System.out.println("getHistoricalPE");
        
        //test of use without first used collectData() method
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           DataCollector t = new DataCollector(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
           t.getHistoricalPE();
        });
        
        //next test
        
        double [] tabTauron = {13.41, 11.59, 10.67, 7.76, 7.53, 7.13, 5.74, 
            5.65, 5.68, 4.58, 5.17, 5.95, 5.85, 8.03, 7.62, 8.23, 7.49, 6.01, 
            6.57, 4.76, 13.59, 8.69, 4.59, 5.11, 3.87, 3.09, 4.25, 2.98, 18.73, 
            38.17, 9.88, 13.14};
        
        double [] tabCdProjekt = {23.99, 37.66, 26.62, 555.43, 497.5, 43.12, 
            12.48, 20.88, 32.37, 28.12, 26.99, 20.93, 20.56, 39.88, 68.24, 111.77, 
            118.0, 123.36, 359.23, 304.23, 9.53, 9.12, 6.14, 6.42, 10.83, 16.12, 
            20.03, 27.1, 35.27, 47.82, 46.56, 59.11, 116.02, 158.19, 128.0, 
            184.42, 190.59, 216.92, 153.24, 111.38, 140.38, 144.14, 23.95, 
            17.53, 16.75, 17.48};
        
        
        assertThat(tauron.getHistoricalPE()).isEqualTo(tabTauron);
        assertThat(cdprojekt.getHistoricalPE()).isEqualTo(tabCdProjekt);
    }

    /**
     * Test of getHistoricalPOE method, of class DataCollector.
     */
    @Test
    public void testGetHistoricalPOE() {
        System.out.println("getHistoricalPOE");
        
        //test of use without first used collectData() method
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           DataCollector t = new DataCollector(new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20"));
           t.getCurrentPOE();
        });
        
        //next test
        double [] tabTauron ={8.23, 7.67, 7.42, 5.61, 5.7, 5.24, 4.08, 3.98, 3.87, 
            3.16, 3.62, 4.14, 3.96, 5.17, 4.96, 5.31, 4.84, 4.33, 4.28, 3.17, 
            6.23, 5.31, 3.29, 3.54, 3.03, 2.28, 2.71, 2.08, 4.85, 5.51, 3.64, 
            3.37, 9.73, 47.17, 9.28, 5.87};
        
        double [] tabCdProjekt ={12.3, 16.25, 24.92, 40.41, 165.52, 35.45, 10.81, 
            18.12, 28.48, 27.06, 26.64, 20.75, 21.15, 40.76, 68.19, 111.97, 112.93, 
            121.7, 445.61, 257.83, 652.22, 7.77, 7.4, 4.96, 5.24, 8.73, 13.06, 
            16.53, 22.37, 29.15, 39.64, 38.7, 48.91, 97.82, 134.06, 124.52, 
            182.99, 186.3, 213.64, 149.02, 107.98, 137.34, 139.69, 23.9, 17.4, 
            16.59, 17.39};
        
        
        assertThat(tauron.getHistoricalPOE()).isEqualTo(tabTauron);
        assertThat(cdprojekt.getHistoricalPOE()).isEqualTo(tabCdProjekt);
    }

    /**
     * Test of getCompany method, of class DataCollector.
     */
    @Test
    public void testGetCompany() {
        System.out.println("getCompany");
        Company t = new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20");
        DataCollector dc = new DataCollector (t);
        
        assertThat(dc.getCompany()).isEqualTo(t);
    }

    /**
     * Test of getCurrentIndicatorsString method, of class DataCollector.
     */
    @Test
    public void testGetCurrentIndicatorsString() {
        System.out.println("getCurrentIndicatorsString");
        
        String str = "WSKAŹNIKI C/WK 9.77 +7.11 >~sector* C/P 7.84 +5.25 >~sector* "
                + "C/Z 16.21 -0.34 >~sector* C/ZO 16.13 +0.36 >~sector* ROE 60.30% "
                + "+48.36% >~sector* ROA 51.47% +43.64% >~sector* raport 2021/Q3 "
                + "* w stosunku do mediany w sektorze zobacz więcej wskaźników »";
        assertThat(cdprojekt.getCurrentIndicatorsString()).isEqualTo(str);
    }

    /**
     * Test of getHistoricalIndicatorsString method, of class DataCollector.
     */
    @Test
    public void testGetHistoricalIndicatorsString() {
        System.out.println("getHistoricalIndicatorsString");
        
        String str = "";
        try {
        String testDirectory = Paths.get("")
                .toAbsolutePath()
                .toString()+File.separator+"src"+File.separator+"test"+File.separator+"java"
                +File.separator+"com"+File.separator+"mycompany"+File.separator
                +"fundamentalanalysis"+File.separator+"testData";
        BufferedReader reader = new BufferedReader(new FileReader(testDirectory+"/historicalIndicatorsTAURON"));
        str = reader.readLine();
        reader.close();
        } catch (Exception e){
            System.out.println(e);
        }
        
        assertThat(tauron.getHistoricalIndicatorsString()).isEqualTo(str);
    }

    /**
     * Test of getHtml method, of class DataCollector.
     */
    @Test
    public void testGetHtml() {
        System.out.println("getHtml");
        
        assertThat(tauron.getHtml()).isEqualTo("https://www.biznesradar.pl/");
    }

    /**
     * Test of setCompany method, of class DataCollector.
     */
    @Test
    public void testSetCompany() {
        System.out.println("setCompany");
        Company t = new Company("TAURON", "Tauron Polska Energia S.A.", "WIG20");
        DataCollector dc = cdprojekt;
        dc.setCompany(t);
        
        assertThat(dc.getCompany()).isEqualTo(t);
    }

    /**
     * Test of setCurrentIndicatorsString method, of class DataCollector.
     */
    @Test
    public void testSetCurrentIndicatorsString() {
        System.out.println("setCurrentIndicatorsString");
        DataCollector dc = cdprojekt;
        String str = "TODO review the generated test code and remove the default call to fail.";
        dc.setCurrentIndicatorsString(str);
        
        assertThat(dc.getCurrentIndicatorsString()).isEqualTo(str);
    }

    /**
     * Test of setHistoricalIndicatorsString method, of class DataCollector.
     */
    @Test
    public void testSetHistoricalIndicatorsString() {
        System.out.println("setHistoricalIndicatorsString");
        DataCollector dc = cdprojekt;
        String str = "TODO review the generated test code and remove the default call to fail.";
        dc.setHistoricalIndicatorsString(str);
        
        assertThat(dc.getHistoricalIndicatorsString()).isEqualTo(str);
    }
    
}
