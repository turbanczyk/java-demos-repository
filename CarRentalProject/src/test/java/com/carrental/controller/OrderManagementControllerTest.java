/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.controller;

import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderManagementControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    public OrderManagementControllerTest() {
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
     * Test of orderManagementPage method, of class OrderManagementController.
     */
    @Test
    public void testOrderManagementPage() throws Exception {
        System.out.println("orderManagementPage");
        
        this.mockMvc.perform(get("/ordermanagement")
                .with(user("user2").password("password2").roles("USER"))
                //.with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("x5 BMW")))
            .andExpect(content().string(containsString("Ford Mondeo")))
            .andExpect(content().string(containsString("Astra Opel")))
            .andExpect(content().string(containsString("Ford Mondeo")))
            .andExpect(content().string(containsString("Astra Opel")))
            .andExpect(content().string(containsString("Ford Mondeo")))
            .andExpect(content().string(containsString("Ford Focus")))
            .andExpect(content().string(containsString("Seria 3 BMW")))
            .andExpect(content().string(containsString("Seria 3 BMW")));
    }
    
}
