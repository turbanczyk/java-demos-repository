/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.fundamentalanalysis;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import static org.assertj.core.api.Assertions.*;
import org.assertj.core.data.Percentage;

/**
 *
 * @author tomeku
 */
public class CompanyTest {
    
    private final Company com;
    
    public CompanyTest() {
        com = new Company ("DNP", "Dino", "WIG20");
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

    /**
     * Test of constructor (first argument), of class Company.
     */
    @Test
    public void testConstructor1() {
        System.out.println("testConstructor1");
        
        //test witc correct data
        Company c = new Company ("DNP", "Dino", "WIG20");
        
        //test with incorrect data
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company (null, "Dino", "WIG20");
        });
        
        Exception thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company ("", "Dino", "WIG20");
        });
    }
    
    /**
     * Test of constructor (second argument), of class Company.
     */
    @Test
    public void testConstructor2() {
        System.out.println("testConstructor2");
        
        //test with correct data
        Company c = new Company ("DNP", "Dino", "WIG20");
        
        //test with incorrect data
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company ("DNP", null, "WIG20");
        });
        
        Exception thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company ("DNP", "", "WIG20");
        });
    }
    
    /**
     * Test of constructor (third argument), of class Company.
     */
    @Test
    public void testConstructor3() {
        System.out.println("testConstructor3");
        
        //test with correct data
        Company c = new Company ("DNP", "Dino", "WIG20");
        c = new Company ("DNP", "Dino", "mWIG40");
        c = new Company ("DNP", "Dino", "sWIG80");
        
        //test with incorrect data
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company ("DNP", "Dino", "jakis");
        });
        
        Exception thrown2 = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Company cc = new Company ("DNP", "Dino", null);
        });
    }
    
    /**
     * Test of calculateHistoricalAveragePE method, of class Company.
     */
    @Test
    public void testCalculateHistoricalAveragePE() {
        System.out.println("calculateHistoricalAveragePE");
        
        //start function without set parameters (data in table)
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           com.calculateHistoricalAveragePE();
        });
        
        //check calculation
        double [] tab = {10.5, 9.5, 20, 40};
        com.setHistoricalPE(tab);
        
        assertThat(com.calculateHistoricalAveragePE()).isEqualTo(20, withPrecision(0.1));
    }

    /**
     * Test of calculateHistoricalAveragePOE method, of class Company.
     */
    @Test
    public void testCalculateHistoricalAveragePOE() {
        System.out.println("calculateHistoricalAveragePOE");
        
        //start function without set parameters (data in table)
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           com.calculateHistoricalAveragePOE();
        });
        
        //check calculation
        double [] tab = {10.5, 9.5, 20, 40};
        com.setHistoricalPOE(tab);
        
        assertThat(com.calculateHistoricalAveragePOE()).isEqualTo(20, withPrecision(0.1));
    }

    /**
     * Test of getCompanyId method, of class Company.
     */
    @Test
    public void testGetCompanyId() {
        assertThat(com.getCompanyId()).isEqualTo("DNP");
    }

    /**
     * Test of getCompanyName method, of class Company.
     */
    @Test
    public void testGetCompanyName() {
        assertThat(com.getCompanyName()).isEqualTo("Dino");
    }

    /**
     * Test of getIndex method, of class Company.
     */
    @Test
    public void testGetIndex() {
        assertThat(com.getIndex()).isEqualTo("WIG20");
    }

    /**
     * Test of getCurrentPE method, of class Company.
     */
    @Test
    public void testGetCurrentPE() {
        Company c = com;
        c.setCurrentPE(5.6);
        assertThat(c.getCurrentPE()).isEqualTo(5.6);
    }

    /**
     * Test of getCurrentPOE method, of class Company.
     */
    @Test
    public void testGetCurrentPOE() {
        Company c = com;
        c.setCurrentPOE(5.68);
        assertThat(c.getCurrentPOE()).isEqualTo(5.68);
    }

    /**
     * Test of getHistoricalPE method, of class Company.
     */
    @Test
    public void testGetHistoricalPE() {
        double [] tab = {10.5, 9.5, 20, 40};
        Company c = com;
        c.setHistoricalPE(tab);
        assertThat(c.getHistoricalPE()).isEqualTo(tab);
    }

    /**
     * Test of getHistoricalPOE method, of class Company.
     */
    @Test
    public void testGetHistoricalPOE() {
        double [] tab = {10.5, 9.5, 20, 45};
        Company c = com;
        c.setHistoricalPOE(tab);
        assertThat(c.getHistoricalPOE()).isEqualTo(tab);
    }

    /**
     * Test of setCompanyId method, of class Company.
     */
    @Test
    public void testSetCompanyId() {
        Company c = com;
        c.setCompanyId("TAU");
        assertThat(c.getCompanyId()).isEqualTo("TAU");
    }

    /**
     * Test of setCompanyName method, of class Company.
     */
    @Test
    public void testSetCompanyName() {
        Company c = com;
        c.setCompanyName("Tauron Polska Energia S.A.");
        assertThat(c.getCompanyName()).isEqualTo("Tauron Polska Energia S.A.");
    }

    /**
     * Test of setIndex method, of class Company.
     */
    @Test
    public void testSetIndex() {
        Company c = com;
        c.setIndex("WIG20");
        assertThat(c.getIndex()).isEqualTo("WIG20");
    }

    /**
     * Test of setCurrentPE method, of class Company.
     */
    @Test
    public void testSetCurrentPE() {
        Company c = com;
        c.setCurrentPE(42.3);
        assertThat(c.getCurrentPE()).isEqualTo(42.3);
    }

    /**
     * Test of setCurrentPOE method, of class Company.
     */
    @Test
    public void testSetCurrentPOE() {
        Company c = com;
        c.setCurrentPOE(31.2);
        assertThat(c.getCurrentPOE()).isEqualTo(31.2);
    }

    /**
     * Test of setHistoricalPE method, of class Company.
     */
    @Test
    public void testSetHistoricalPE() {
        double [] tab = {2, 8.5, 20, 45};
        Company c = com;
        c.setHistoricalPE(tab);
        assertThat(c.getHistoricalPE()).isEqualTo(tab);
    }

    /**
     * Test of setHistoricalPOE method, of class Company.
     */
    @Test
    public void testSetHistoricalPOE() {
        double [] tab = {2, 8.5, 30, 45};
        Company c = com;
        c.setHistoricalPOE(tab);
        assertThat(c.getHistoricalPOE()).isEqualTo(tab);
    }

    /**
     * Test of percentageDeviationFromAverage method, of class Company.
     */
    @Test
    public void testPercentageDeviationFromAverage() {
        System.out.println("percentageDeviationFromAverage");
        //check with currentValue 0
        assertThat(com.percentageDeviationFromAverage(0, 9)).isEqualTo(0);
        //next
        assertThat(com.percentageDeviationFromAverage(9.85, 29.67)).isCloseTo(201.22, Percentage.withPercentage(0.1));
        assertThat(com.percentageDeviationFromAverage(9.46, 6.16)).isCloseTo(-34.88, Percentage.withPercentage(0.1));
        assertThat(com.percentageDeviationFromAverage(8.9, 12.07)).isCloseTo(35.62, Percentage.withPercentage(0.1));
    }
    
}
