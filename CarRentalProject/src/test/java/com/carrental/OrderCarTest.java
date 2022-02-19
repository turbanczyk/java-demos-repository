/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import java.time.LocalDate;
import java.time.Month;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
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
        
        /*
        //User ID can't be empty or null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar(null, "123456", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
        
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           OrderCar testOrder = new OrderCar("", "123456", LocalDate.of(2022, 2, 17),
                LocalDate.of(2022, 2, 19), LocalDate.of(2022, 2, 15), 432);
        });
        */
        
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
    
    /**
     * Test of getId method, of class OrderCar.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        
        //assertThat(orderCar.getId()).isNotEmpty();
        assertThat(orderCar.getId()).isNotNull();
    }

    /**
     * Test of getUserId method, of class OrderCar.
     */
    @Test
    public void testGetUserId() {
        System.out.println("getUserId");
        
        //assertThat(orderCar.getUserId()).isEqualTo(384050);
    }

    /**
     * Test of getCarId method, of class OrderCar.
     */
    @Test
    public void testGetCarId() {
        System.out.println("getCarId");
        
        assertThat(orderCar.getCarId()).isEqualTo("123456");
    }

    /**
     * Test of getStartDate method, of class OrderCar.
     */
    @Test
    public void testGetStartDate() {
        System.out.println("getStartDate");
        
        assertThat(orderCar.getStartDate()).isEqualTo(LocalDate.of(2022, 2, 17));
    }

    /**
     * Test of getEndDate method, of class OrderCar.
     */
    @Test
    public void testGetEndDate() {
        System.out.println("getEndDate");
        
        assertThat(orderCar.getEndDate()).isEqualTo(LocalDate.of(2022, 2, 19));
    }

    /**
     * Test of getPlacedAt method, of class OrderCar.
     */
    @Test
    public void testGetPlacedAt() {
        System.out.println("getPlacedAt");
        
        assertThat(orderCar.getPlacedAt()).isEqualTo(LocalDate.of(2022, 2, 15));
    }

    /**
     * Test of setId method, of class OrderCar.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        
        orderCar.setId(384050);
        assertThat(orderCar.getId()).isEqualTo(384050);
    }

    /**
     * Test of setUserId method, of class OrderCar.
     */
    @Test
    public void testSetUserId() {
        System.out.println("setUserId");
        
        orderCar.setUserId(384050);
        assertThat(orderCar.getUserId()).isEqualTo(384050);
    }

    /**
     * Test of setCarId method, of class OrderCar.
     */
    @Test
    public void testSetCarId() {
        System.out.println("setCarId");
        
        orderCar.setCarId("384050");
        assertThat(orderCar.getCarId()).isEqualTo("384050");
    }

    /**
     * Test of setStartDate method, of class OrderCar.
     */
    @Test
    public void testSetStartDate() {
        System.out.println("setStartDate");
        
        orderCar.setStartDate(LocalDate.of(2022, 2, 16));
        assertThat(orderCar.getStartDate()).isEqualTo(LocalDate.of(2022, 2, 16));
    }

    /**
     * Test of setEndDate method, of class OrderCar.
     */
    @Test
    public void testSetEndDate() {
        System.out.println("setEndDate");
        
        orderCar.setEndDate(LocalDate.of(2022, 2, 25));
        assertThat(orderCar.getEndDate()).isEqualTo(LocalDate.of(2022, 2, 25));
    }

    /**
     * Test of setPlacedAt method, of class OrderCar.
     */
    @Test
    public void testSetPlacedAt() {
        System.out.println("setPlacedAt");
        
        orderCar.setPlacedAt(LocalDate.of(2022, 2, 25));
        assertThat(orderCar.getPlacedAt()).isEqualTo(LocalDate.of(2022, 2, 25));
    }
    
}
