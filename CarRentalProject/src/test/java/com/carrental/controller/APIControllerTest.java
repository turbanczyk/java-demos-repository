/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.controller;

import com.carrental.entity.Car;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ActiveProfiles;


/**
 *
 * @author tomeku
 */
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
public class APIControllerTest {
    
    @Autowired
    private TestRestTemplate restTemplate;
    @LocalServerPort
    private int port;
    
    public APIControllerTest() {
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
     * Test of getCarList method, of class APIController.
     */
    @Test
    public void testGetCarList() {
        System.out.println("getCarList");
        
        String url = "http://localhost:" + port + "/api/carlist/{localization}/{rentStart}/{rentEnd}";
        Map<String, String> map = new HashMap<>();
        map.put("localization", "Gliwice");
        map.put("rentStart", "2022-03-12");
        map.put("rentEnd", "2022-03-14");
        Car[] cars = restTemplate.getForObject(url, Car[].class, map);
        assertThat(cars.length).isEqualTo(3);
        
    }
    
}
