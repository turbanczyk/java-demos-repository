/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarOrderRepository;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

import static org.hamcrest.Matchers.containsString;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpSession;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.anonymous;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class OrderCarControllerTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    CarOrderRepository carOrderRepository;
    
    public OrderCarControllerTest() {
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
     * Test of orderPage method, of class OrderCarController.
     */
    @Test
    public void testOrderPage() throws Exception {
        System.out.println("orderPage");
        
        //create session attribute carOrder
        MockHttpSession session = new MockHttpSession();

        OrderCar orderCar = new OrderCar();
        LocalDate rentStartDate = LocalDate.of(2022, 1, 19);
        LocalDate rentEndDate = LocalDate.of(2022, 1, 22);
        String carId = "samochod-4-id";
        double totalPrice = 214*4;
        orderCar.setCarId(carId);
        orderCar.setStartDate(rentStartDate);
        orderCar.setEndDate(rentEndDate);
        orderCar.setTotalPrice(totalPrice);

        session.setAttribute("carOrder", orderCar);
        
        this.mockMvc.perform(post("/order")
                .param("carId", carId)
                .session(session)
                //.with(user("user1").password("password1").roles("USER"))
                .with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Seria 3 BMW")))
            .andExpect(content().string(containsString("Data rozpoczęcia rezerwacji: 2022-01-19")))
            .andExpect(content().string(containsString("Data zakończenia rezerwacji: 2022-01-22")))
            .andExpect(content().string(containsString("Miejsce odbioru i zwrotu samochodu: Gliwice")))
            .andExpect(content().string(containsString("Cena całkowita: 856.0 zł")));
        
        
    }

    /**
     * Test of orderConfirmationPage method, of class OrderCarController.
     */
    @Test
    public void testOrderConfirmationPage() throws Exception {
        System.out.println("orderConfirmationPage");

        //create session attribute carOrder
        MockHttpSession session = new MockHttpSession();

        OrderCar orderCar = new OrderCar();
        LocalDate rentStartDate = LocalDate.of(2023, 1, 19);
        LocalDate rentEndDate = LocalDate.of(2023, 1, 22);
        String carId = "samochod-4-id";
        double totalPrice = 214*4;
        //int userId = 12343212;
        int userId = 1;
        
        orderCar.setCarId(carId);
        orderCar.setStartDate(rentStartDate);
        orderCar.setEndDate(rentEndDate);
        orderCar.setTotalPrice(totalPrice);
        orderCar.setUserId(userId);
        orderCar.setPlacedAt(LocalDate.now());

        session.setAttribute("carOrder", orderCar);
        
        this.mockMvc.perform(get("/orderconfirmation")
                .session(session)
                .with(user("user1").password("password1").roles("USER"))
                //.with(anonymous())
                .with(csrf())
                )
            //.andDo(print())
            .andExpect(status().isOk())
            .andExpect(content().string(containsString("Dziękujemy, Twoje rezerwacja zakończyła się sukcesem.")));
        
        
        List<OrderCar> userOrders = carOrderRepository.findByUserId(userId);
        assertThat(userOrders.size()).isEqualTo(3);
        
    }
    
}
