/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.dto;

import com.carrental.dto.TimePeriod;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Assertions;

import static org.assertj.core.api.Assertions.assertThat;


/**
 *
 * @author tomeku
 */
public class TimePeriodTest {
    
    private TimePeriod timePeriod;
    
    public TimePeriodTest() {
        timePeriod = new TimePeriod(LocalDate.of(2022, 1, 22), 
                LocalDate.of(2022, 1, 25));
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
     * Test of Constructor, of class TimePeriod.
     */
    @Test
    public void testConstructor() {
        System.out.println("getStartDate");
        LocalDate startDate = LocalDate.of(2022, 1, 22);
        LocalDate endDate = LocalDate.of(2022, 1, 25);
        
        //check correct situation
        TimePeriod f = new TimePeriod(startDate, endDate);
        
        //check null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           TimePeriod c = new TimePeriod(null, endDate);
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           TimePeriod c = new TimePeriod(startDate, null);
        });
        
        //check mismatch
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           TimePeriod c = new TimePeriod(startDate, LocalDate.of(2022, 1, 20));
        });
        
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           TimePeriod c = new TimePeriod(LocalDate.of(2022, 1, 26), endDate);
        });
        
    }
    
}
