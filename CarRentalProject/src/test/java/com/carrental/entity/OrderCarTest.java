/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.entity;


import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

/**
 *
 * @author tomeku
 */
public class OrderCarTest {
    
    OrderCar orderCar;
            
    public OrderCarTest() {
        orderCar = new OrderCar("123456", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
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
        
        //Car ID can't be empty or null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
 
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar(null, LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
        
        //Start date can't be null
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("3456", null,
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
        
        //End date can't be null
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("3456", LocalDate.of(2022, 2, 17),
                null, LocalDate.of(2022, 2, 15), 432);
        });
        
        //Start date can't be after end date
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("35678", LocalDate.of(2022, 2, 20),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
        
        //End date can't be before start date
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("3456677", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 16), LocalDate.of(2022, 2, 15), 432);
        });
        
        //Placed at can't be null
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("334656", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), null, 432);
        });
        
        //Total price must be bigger than 0
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("334656", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), -1);
        });
        
    }
    
}
