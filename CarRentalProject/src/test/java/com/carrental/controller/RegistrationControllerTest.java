/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.controller;

import com.carrental.entity.User;
import com.carrental.data.UserRepository;

import static org.assertj.core.api.Assertions.assertThat;

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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class RegistrationControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    UserRepository userRepo;
    
    public RegistrationControllerTest() {
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
     * Test of registerForm method, of class RegistrationController.
     */
    @Test
    public void testRegisterForm() throws Exception {
        System.out.println("registerForm");
        
        this.mockMvc.perform(get("/register")
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("<label for=\"password\">Hasło: </label>")))
            .andExpect(content().string(containsString("<label for=\"city\">Miejscowość: </label>")))
            .andExpect(content().string(containsString("<label for=\"drivingLicenseNumber\">Numer prawa jazdy: </label>")))
            .andExpect(content().string(containsString("<label for=\"email\">Email: </label>")))
            .andExpect(content().string(containsString("<input type=\"submit\" value=\"Zarejestruj\"/>")));
    }

    /**
     * Test of processRegistration method, of class RegistrationController.
     */
    @Test
    public void testProcessRegistration() throws Exception {
        System.out.println("processRegistration");
        
        String username = "user3";
        String password = "tajnehaslo";
        String name = "ola";
        String surname = "nowak";
        String country = "Polska";
        String city = "Warszawa";
        String street = "główna";
        String drivingLicenseNumber = "32104";
        String email = "ola@ola.com";
        String telephoneNumber = "123123567";
        
        
        this.mockMvc.perform(post("/register")
                .param("username", username)
                .param("password", password)
                .param("name", name)
                .param("surname", surname)
                .param("country", country)
                .param("city", city)
                .param("street", street)
                .param("drivingLicenseNumber", drivingLicenseNumber)
                .param("email", email)
                .param("telephoneNumber", telephoneNumber)
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                );
            //.andDo(print());
        
        User user = userRepo.findByUsername(username);
        assertThat(user).isNotNull();
    }
    
}
