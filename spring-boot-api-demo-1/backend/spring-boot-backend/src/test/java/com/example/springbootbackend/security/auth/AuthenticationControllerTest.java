package com.example.springbootbackend.security.auth;

import com.example.springbootbackend.configuration.AppProperties;
import com.example.springbootbackend.security.user.User;
import com.example.springbootbackend.security.user.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestPropertySource({"classpath:application-test.properties"})
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @Autowired
    AppProperties appProperties;
    @Autowired
    UserRepository userRepository;
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ModelMapper modelMapper;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    @WithMockUser(roles = {"USER"})
    void register() {
        try{
            RegisterRequest registerRequest = new RegisterRequest("Anna",
                    "Green", "anna@green.com", "pass123");

            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Origin", appProperties.getClientUrl());
            httpHeaders.add("Host", "localhost:8081");

            MvcResult result = this.mockMvc
                    .perform(post("/api/v1/authentication/register")
                            .headers(httpHeaders)
                            .content(objectMapper.writeValueAsString(registerRequest))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            AuthenticationResponse authenticationResponse =
                    objectMapper.readValue(result.getResponse().getContentAsString(), AuthenticationResponse.class);

            assertNotNull(authenticationResponse.getToken());

            //clean
            userRepository.delete(modelMapper.map(registerRequest, User.class));

        }catch (Exception e) {fail();}
    }

    @Test
    void authenticate() {
        try {
            //positive scenario
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Origin", appProperties.getClientUrl());
            httpHeaders.add("Host", "localhost:8081");

            List<User> users = userRepository.findAll();
            User user = users.get(new Random().nextInt(users.size()));

            AuthenticationRequest request = new AuthenticationRequest(user.getEmail(), user.getPassword());

            MvcResult result = this.mockMvc
                    .perform(post("/api/v1/authentication/authenticate")
                            .headers(httpHeaders)
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().isOk())
                    .andReturn();

            //negative scenario 1
            user = users.get(new Random().nextInt(users.size()));
            user.setPassword("wrong");

            request = new AuthenticationRequest(user.getEmail(), user.getPassword());

            result = this.mockMvc
                    .perform(post("/api/v1/authentication/authenticate")
                            .headers(httpHeaders)
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(403))
                    .andReturn();

            //negative scenario 2
            user = users.get(new Random().nextInt(users.size()));
            user.setEmail("efpyi@example.com");

            request = new AuthenticationRequest(user.getEmail(), user.getPassword());

            result = this.mockMvc
                    .perform(post("/api/v1/authentication/authenticate")
                            .headers(httpHeaders)
                            .content(objectMapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON))
                    .andDo(print())
                    .andExpect(status().is(403))
                    .andReturn();

        }catch (Exception e) {fail();}

    }
}