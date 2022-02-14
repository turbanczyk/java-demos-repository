/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.data;

import com.carrental.OrderCar;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Disabled;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
public class CarOrderRepositoryTest {
    
    private CarOrderRepository carOrderRepository;
    
    @Autowired
    public CarOrderRepositoryTest(CarOrderRepository carOrderRepository) {
        this.carOrderRepository = carOrderRepository;
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
     * Test of findByStartDateIsAfter method, of class CarOrderRepository.
     */
    @Test
    public void testFindByStartDateIsAfter() {
        System.out.println("findByStartDateIsAfter");
        List<OrderCar> carOrder = carOrderRepository.findByStartDateIsAfter(LocalDate.of(2022, 1, 22));
        assertThat(carOrder.size()).isEqualTo(2);
        
        assertThat(carOrder.get(1).getId()).isEqualTo("zamowienie-3-id");
        assertThat(carOrder.get(1).getUserId()).isEqualTo("user-2-id");
        assertThat(carOrder.get(1).getCarId()).isEqualTo("samochod-2-id");
        assertThat(carOrder.get(1).getStartDate()).isEqualTo(LocalDate.of(2022, 1, 23));
        assertThat(carOrder.get(1).getEndDate()).isEqualTo(LocalDate.of(2022, 2, 18));
        assertThat(carOrder.get(1).getPlacedAt()).isEqualTo(LocalDate.of(2022, 1, 15));
    }

    
}
