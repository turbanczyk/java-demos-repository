/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import java.time.LocalDate;
import java.time.Month;
import javax.servlet.http.HttpServletRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.security.core.Authentication;
import org.springframework.ui.Model;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author tomeku
 */
public class OrderCarControllerTest {
    
    public OrderCarControllerTest() {
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
     * Test of orderPage method, of class OrderCarController.
     */
    @Test
    @Disabled
    public void testOrderPage() {
        System.out.println("orderPage");

    }

    /**
     * Test of orderConfirmationPage method, of class OrderCarController.
     */
    @Test
    @Disabled
    public void testOrderConfirmationPage() {
        System.out.println("orderConfirmationPage");

    }

    /**
     * Test of countAmountOfDays method, of class OrderCarController.
     */
    /*
    @Test
    public void testCountAmountOfDays() {
        System.out.println("countAmountOfDays");
        LocalDate startDate = LocalDate.of(2022, 2, 22);
        LocalDate endDate = LocalDate.of(2022, 2, 24);
        
        new OrderCarController().countAmountOfDays(startDate, endDate);
    }
    */
    
}
