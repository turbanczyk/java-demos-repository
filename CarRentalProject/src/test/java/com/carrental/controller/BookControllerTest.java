/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.controller;

import java.time.LocalDate;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import static org.hamcrest.Matchers.containsString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
//@ExtendWith(SpringExtension.class)
public class BookControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
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
    
    /**
     * Test of page method, of class BookController.
     */
    @Test
    public void testBookPage() throws Exception {
        System.out.println("bookPage");
        

        String localization = "Gliwice";
        LocalDate rentStartDate = LocalDate.of(2022, 1, 19);
        LocalDate rentEndDate = LocalDate.of(2022, 1, 22);
        
        this.mockMvc.perform(post("/book")
                .param("localization", localization)
                .param("rentStart", rentStartDate.toString())
                .param("rentEnd", rentEndDate.toString())
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("onchange=\"show_value(this.value);\" min=\"114.0\" max=\"214.0\">")))
            .andExpect(content().string(containsString("Ford Focus")))
            .andExpect(content().string(containsString("<input type=\"hidden\" name=\"carId\" value=\"samochod-3-id\"/>")))
            .andExpect(content().string(containsString("<h3>Seria 3 BMW</h3>")))
            .andExpect(content().string(containsString("<input type=\"hidden\" name=\"carId\" value=\"samochod-4-id\"/>")));
            
        


        localization = "Grenlandia";
        this.mockMvc.perform(post("/book")
                .param("localization", localization)
                .param("rentStart", rentStartDate.toString())
                .param("rentEnd", rentEndDate.toString())
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<h2>Brak dostępnych samochodów</h2>")));
        
        
    }
    
}
