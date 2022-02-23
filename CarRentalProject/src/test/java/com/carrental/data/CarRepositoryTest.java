/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental.data;

import com.carrental.service.BookService;
import com.carrental.entity.Car;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 *
 * @author tomeku
 */
@SpringBootTest
@ActiveProfiles("test")
public class CarRepositoryTest {
    
    private CarRepository carRepository;
    
    @Autowired
    public CarRepositoryTest(CarRepository carRepository) {
        this.carRepository = carRepository;
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
     * Test of findAll method, of class CarRepository.
     */
    @Test
    public void testFindAll() {
        System.out.println("findAll");
        List<Car> carsList = carRepository.findAll();
        //check amount of records
        assertThat(carsList.size()).isEqualTo(8);
        
        //check records
        assertThat(carsList.get(0).getId()).isEqualTo("samochod-1-id");
        assertThat(carsList.get(0).getModel()).isEqualTo("Mondeo");
        assertThat(carsList.get(0).getBrand()).isEqualTo("Ford");
        assertThat(carsList.get(0).getPricePerDay()).isEqualTo(130);
        assertThat(carsList.get(0).getNumberOfSeats()).isEqualTo(5);
        assertThat(carsList.get(0).getDailyKilometerLimit()).isEqualTo(400);
        assertThat(carsList.get(0).getGearboxType()).isEqualTo("manualna");
        assertThat(carsList.get(0).isAirConditioning()).isEqualTo(true);
        assertThat(carsList.get(0).getMileage()).isEqualTo(12345);
        assertThat(carsList.get(0).getCategory()).isEqualTo("małe");
        assertThat(carsList.get(0).getLocalization()).isEqualTo("Częstochowa");
        
        assertThat(carsList.get(2).getId()).isEqualTo("samochod-3-id");
        assertThat(carsList.get(2).getModel()).isEqualTo("Focus");
        assertThat(carsList.get(2).getBrand()).isEqualTo("Ford");
        assertThat(carsList.get(2).getPricePerDay()).isEqualTo(114);
        assertThat(carsList.get(2).getNumberOfSeats()).isEqualTo(5);
        assertThat(carsList.get(2).getDailyKilometerLimit()).isEqualTo(300);
        assertThat(carsList.get(2).getGearboxType()).isEqualTo("manualna");
        assertThat(carsList.get(2).isAirConditioning()).isEqualTo(true);
        assertThat(carsList.get(2).getMileage()).isEqualTo(15341);
        assertThat(carsList.get(2).getCategory()).isEqualTo("kombi");
        assertThat(carsList.get(2).getLocalization()).isEqualTo("Gliwice");
    }

    /**
     * Test of findByLocalizationIs method, of class CarRepository.
     */
    @Test
    public void testFindByLocalizationIs() {
        System.out.println("findByLocalizationIs");
        List<Car> carsList = carRepository.findByLocalizationIs("Gliwice");
        assertThat(carsList.size()).isEqualTo(3);
    }


    
}
