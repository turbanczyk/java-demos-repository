package com.example.springbootbackend.security.configuration;

import com.example.springbootbackend.configuration.AppProperties;
import com.example.springbootbackend.dto.ProductDto;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource({"classpath:application-test.properties"})
@AutoConfigureMockMvc
@WithMockUser(roles = {"USER"})
class CorsConfigTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AppProperties appProperties;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void corsFilter() {
        try {
            //first scenario
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Origin", appProperties.getClientUrl());
            httpHeaders.add("Host", "localhost:8081");

            MvcResult result = this.mockMvc
                    .perform(get("/api/v1/products")
                            .headers(httpHeaders))
                    //.andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            //second scenario
            httpHeaders = new HttpHeaders();
            httpHeaders.add("Origin", "http://localhost:8912");
            httpHeaders.add("Host", "localhost:8081");

            result = this.mockMvc
                   .perform(get("/api/v1/products")
                           .headers(httpHeaders))
                    //.andDo(print())
                   .andExpect(status().is(403))
                   .andReturn();

            //third scenario
            httpHeaders = new HttpHeaders();
            httpHeaders.add("Origin", "www.example.com");
            httpHeaders.add("Host", "localhost:8081");

            result = this.mockMvc
                    .perform(get("/api/v1/products")
                            .headers(httpHeaders))
                    //.andDo(print())
                    .andExpect(status().is(403))
                    .andReturn();

        }catch (Exception e) {fail();}
    }
}