/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;
import java.time.LocalDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.List;
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
public class BookAssistantTest {
    
    private CarRepository carRepository;
    
    @Autowired
    public BookAssistantTest(CarRepository carRepository) {
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
     * Test of isTimePeriodOutOfTimePeriod method, of class BookAssistant.
     */
    @Test
    public void testIsTimePeriodOutOfTimePeriod() {
        System.out.println("isTimePeriodOutOfTimePeriod");
        BookAssistant bookAssistant = new BookAssistant();
        TimePeriod basicPeriod = new TimePeriod(LocalDate.of(2022, 1, 22), LocalDate.of(2022, 1, 25));
        
        ArrayList<TimePeriod> listOfTimePeriodTrue = new ArrayList<TimePeriod>();
        listOfTimePeriodTrue.add(new TimePeriod(LocalDate.of(2022, 1, 19), LocalDate.of(2022, 1, 21)));
        listOfTimePeriodTrue.add(new TimePeriod(LocalDate.of(2022, 1, 26), LocalDate.of(2022, 1, 27)));
        for (int i = 0; i < listOfTimePeriodTrue.size(); i++) {
            assertThat(bookAssistant.isTimePeriodOutOfTimePeriod(listOfTimePeriodTrue.get(i), basicPeriod)).isEqualTo(true);
        }
        for (int i = listOfTimePeriodTrue.size() - 1; i >= 0; i--) {
            assertThat(bookAssistant.isTimePeriodOutOfTimePeriod(listOfTimePeriodTrue.get(i), basicPeriod)).isEqualTo(true);
        }
        
        ArrayList<TimePeriod> listOfTimePeriodFalse = new ArrayList<TimePeriod>();
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 19), LocalDate.of(2022, 1, 22)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 19), LocalDate.of(2022, 1, 23)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 22), LocalDate.of(2022, 1, 24)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 22), LocalDate.of(2022, 1, 25)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 23), LocalDate.of(2022, 1, 24)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 23), LocalDate.of(2022, 1, 27)));
        listOfTimePeriodFalse.add(new TimePeriod(LocalDate.of(2022, 1, 25), LocalDate.of(2022, 1, 27)));
        for (int i = 0; i < listOfTimePeriodFalse.size(); i++) {
            assertThat(bookAssistant.isTimePeriodOutOfTimePeriod(listOfTimePeriodFalse.get(i), basicPeriod)).isEqualTo(false);
        }
        for (int i = listOfTimePeriodFalse.size() - 1; i >= 0; i--) {
            assertThat(bookAssistant.isTimePeriodOutOfTimePeriod(listOfTimePeriodFalse.get(i), basicPeriod)).isEqualTo(false);
        }
    }

    /**
     * Test of getCarsAvailableInTimePeriodAndLocalization method, of class BookAssistant.
     */
    @Test
    @Disabled
    public void testGetCarsAvailableInTimePeriodAndLocalization() {
        System.out.println("getCarsAvailableInTimePeriodAndLocalization");
        BookAssistant bookAssistant = new BookAssistant(carRepository);
        
    }
    
}
