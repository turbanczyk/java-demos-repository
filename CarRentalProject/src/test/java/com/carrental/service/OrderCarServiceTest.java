/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.entity.OrderCar;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
public class OrderCarServiceTest {
    
    @Autowired
    private OrderCarService orderCarService;
    
    public OrderCarServiceTest() {
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
     * Test of calculateTotalPrice method, of class OrderCarService.
     */
    @Test
    public void testCalculateTotalPrice() {
        System.out.println("calculateTotalPrice");
        OrderCar orderCar = new OrderCar();
        orderCar.setStartDate(LocalDate.of(2022, 3, 24));
        orderCar.setEndDate(LocalDate.of(2022, 3, 26));
        orderCar.setCarId("samochod-1-id");
        
        assertThat(orderCarService.calculateTotalPrice(orderCar)).isEqualTo(390);
    }

    /**
     * Test of getCar method, of class OrderCarService.
     */
    @Test
    public void testGetCar() {
        System.out.println("getCar");
        
        OrderCar orderCar = new OrderCar();
        orderCar.setCarId("samochod-1-id");
        
        Car car = orderCarService.getCar(orderCar);
        assertThat(car.getId()).isEqualTo("samochod-1-id");
        assertThat(car.getLocalization()).isEqualTo("Częstochowa");
    }

    /**
     * Test of saveOrderCar method, of class OrderCarService.
     */
    @Test
    public void testSaveOrderCar() {
        System.out.println("saveOrderCar");
        
        OrderCar orderCar = new OrderCar();
        orderCar.setCarId("samochod-1-id");
        orderCar.setStartDate(LocalDate.of(2024, 3, 24));
        orderCar.setEndDate(LocalDate.of(2024, 3, 26));
        orderCar.setTotalPrice(390);
        orderCar.setUserId(1);
        orderCar.setPlacedAt(LocalDate.now());
        
        //assertThat(orderCarService.saveOrderCar(orderCar, "user1")).isTrue();
        
    }
    
}
