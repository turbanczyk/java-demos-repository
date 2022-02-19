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
        assertThat(carOrder.size()).isEqualTo(6);
        
        
        assertThat(carOrder.get(0).getId()).isEqualTo(6);
        assertThat(carOrder.get(0).getUserId()).isEqualTo(2);
        assertThat(carOrder.get(0).getCarId()).isEqualTo("samochod-1-id");
        assertThat(carOrder.get(0).getStartDate()).isEqualTo(LocalDate.of(2022, 1, 23));
        assertThat(carOrder.get(0).getEndDate()).isEqualTo(LocalDate.of(2022, 1, 24));
        assertThat(carOrder.get(0).getPlacedAt()).isEqualTo(LocalDate.of(2022, 1, 15));
        
        assertThat(carOrder.get(1).getId()).isEqualTo(7);
        assertThat(carOrder.get(1).getUserId()).isEqualTo(2);
        assertThat(carOrder.get(1).getCarId()).isEqualTo("samochod-8-id");
        assertThat(carOrder.get(1).getStartDate()).isEqualTo(LocalDate.of(2022, 1, 23));
        assertThat(carOrder.get(1).getEndDate()).isEqualTo(LocalDate.of(2022, 1, 27));
        assertThat(carOrder.get(1).getPlacedAt()).isEqualTo(LocalDate.of(2022, 1, 15));
    }

    /**
     * Test of findAll method, of class CarOrderRepository.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<OrderCar> carOrder = carOrderRepository.findAll();
        assertThat(carOrder.size()).isEqualTo(11);
        
        assertThat(carOrder.get(5).getId()).isEqualTo(6);
        assertThat(carOrder.get(5).getUserId()).isEqualTo(2);
        assertThat(carOrder.get(5).getCarId()).isEqualTo("samochod-1-id");
        assertThat(carOrder.get(5).getStartDate()).isEqualTo(LocalDate.of(2022, 1, 23));
        assertThat(carOrder.get(5).getEndDate()).isEqualTo(LocalDate.of(2022, 1, 24));
        assertThat(carOrder.get(5).getPlacedAt()).isEqualTo(LocalDate.of(2022, 1, 15));
        
        assertThat(carOrder.get(6).getId()).isEqualTo(7);
        assertThat(carOrder.get(6).getUserId()).isEqualTo(2);
        assertThat(carOrder.get(6).getCarId()).isEqualTo("samochod-8-id");
        assertThat(carOrder.get(6).getStartDate()).isEqualTo(LocalDate.of(2022, 1, 23));
        assertThat(carOrder.get(6).getEndDate()).isEqualTo(LocalDate.of(2022, 1, 27));
        assertThat(carOrder.get(6).getPlacedAt()).isEqualTo(LocalDate.of(2022, 1, 15));
    }

    
}
