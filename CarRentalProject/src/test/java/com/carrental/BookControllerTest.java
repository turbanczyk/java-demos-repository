/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import javax.servlet.http.HttpServletRequest;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ExtendWith(MockitoExtension.class)
public class BookControllerTest {
    
    @Autowired
    private BookController controller;
    
    @Autowired
    private MockMvc mockMvc;
    
    @Mock
    HttpServletRequest request;
    
    public BookControllerTest() {
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
    public void contextLoads() throws Exception {
        System.out.println("contextLoads");
        assertThat(controller).isNotNull();
    }
    
    /**
     * Test of page method, of class BookController.
     */
    @Test
    @Disabled
    public void testBookPage() throws Exception {
        System.out.println("bookPage");
        
        when(request.getParameter("localization")).thenReturn("Rybnik");
        when(request.getParameter("rentStart")).thenReturn("2022-02-22");
        when(request.getParameter("rentEnd")).thenReturn("2022-02-25");
        
        //Problem with mockito, this part doesn't work. Test disabled on this moment
        //this.mockMvc.perform(post("/book")).andDo(print());
    }
    
}
