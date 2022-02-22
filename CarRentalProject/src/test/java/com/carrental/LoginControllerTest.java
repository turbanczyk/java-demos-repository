/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

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
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class LoginControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    public LoginControllerTest() {
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
     * Test of loginPage method, of class LoginController.
     */
    @Test
    public void testLoginPage() throws Exception {
        System.out.println("loginPage");
        
        this.mockMvc.perform(get("/login")
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Logowanie")))
            .andExpect(content().string(containsString("<form method=\"POST\" action=\"/login\" id=\"loginForm\">")))
            .andExpect(content().string(containsString("<input type=\"text\" name=\"username\" id=\"username\" />")))
            .andExpect(content().string(containsString("<input type=\"password\" name=\"password\" id=\"password\" />")))
            .andExpect(content().string(containsString("<input type=\"submit\" value=\"Zaloguj się\"/>")));
        
    }
    
}
