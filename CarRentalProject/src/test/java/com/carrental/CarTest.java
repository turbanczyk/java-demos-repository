/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.carrental;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author tomeku
 */
public class CarTest {
    
    private Car newCar;
    
    public CarTest() {
        newCar = new Car("Mondeo", "Ford", 120, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
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

    @Test
    public void testConstructor() {
        System.out.println("testConstructor");
        
        //Car model can't be empty or null
        Exception thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car(null, "Ford", 120, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("", "Ford", 120, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        
        //Car brand can't be empty or null
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", null, 120, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "", 120, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        
        //Price per day can't be below 0
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", -1, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        Car i = new Car("Mondeo", "Ford", 250, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        
        //Number of seats can't be smaller than 0 and bigger than 10
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, -1, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        
        i = new Car("Mondeo", "Ford", 250, 1, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 10, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        
        //Daily kilometer limit can't be below 0
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, -1, "manualna", true, 
                12345, "średnie", "Katowice");
        });
        
        //Gearbox type can be only manualna lub automatyczna
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 456, "inna", true, 
                12345, "średnie", "Katowice");
        });
        i = new Car("Mondeo", "Ford", 250, 5, 500, "manualna", true, 
                12345, "średnie", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "średnie", "Katowice");
        
        //Mileage can't be below 0
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 456, "automatyczna", true, 
                -1, "średnie", "Katowice");
        });

        //Category can be only: małe, średnie, duże, kombi, premium, minivany, SUV"
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 456, "automatyczna", true, 
                23456, "inne", "Katowice");
        });
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "małe", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "średnie", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "duże", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "kombi", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "premium", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "minivany", "Katowice");
        i = new Car("Mondeo", "Ford", 250, 5, 500, "automatyczna", true, 
                12345, "SUV", "Katowice");
        
        //Localization can't be empty or null
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 456, "automatyczna", true, 
                23456, "średnie", null);
        });
        thrown = Assertions.assertThrows(IllegalArgumentException.class, () -> {
           Car testCar = new Car("Mondeo", "Ford", 250, 11, 456, "automatyczna", true, 
                23456, "średnie", "");
        });

        
    }
    /**
     * Test of getId method, of class Car.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        assertThat(newCar.getId()).isNotEmpty();
        assertThat(newCar.getId()).isNotEmpty();
    }

    /**
     * Test of getModel method, of class Car.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        assertThat(newCar.getModel()).isEqualTo("Mondeo");
    }

    /**
     * Test of getBrand method, of class Car.
     */
    @Test
    public void testGetBrand() {
        System.out.println("getBrand");
        assertThat(newCar.getBrand()).isEqualTo("Ford");
    }

    /**
     * Test of getPricePerDay method, of class Car.
     */
    @Test
    public void testGetPricePerDay() {
        System.out.println("getPricePerDay");
        assertThat(newCar.getPricePerDay()).isEqualTo(120);
    }

    /**
     * Test of getNumberOfSeats method, of class Car.
     */
    @Test
    public void testGetNumberOfSeats() {
        System.out.println("getNumberOfSeats");
        assertThat(newCar.getNumberOfSeats()).isEqualTo(5);
    }

    /**
     * Test of getDailyKilometerLimit method, of class Car.
     */
    @Test
    public void testGetDailyKilometerLimit() {
        System.out.println("getDailyKilometerLimit");
        assertThat(newCar.getDailyKilometerLimit()).isEqualTo(500);
    }

    /**
     * Test of getGearboxType method, of class Car.
     */
    @Test
    public void testGetGearboxType() {
        System.out.println("getGearboxType");
        assertThat(newCar.getGearboxType()).isEqualTo("manualna");
    }

    /**
     * Test of isAirConditioning method, of class Car.
     */
    @Test
    public void testIsAirConditioning() {
        System.out.println("isAirConditioning");
        assertThat(newCar.isAirConditioning()).isEqualTo(true);
    }

    /**
     * Test of getMileage method, of class Car.
     */
    @Test
    public void testGetMileage() {
        System.out.println("getMileage");
        assertThat(newCar.getMileage()).isEqualTo(12345);
    }

    /**
     * Test of getCategory method, of class Car.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        assertThat(newCar.getCategory()).isEqualTo("średnie");
    }

    /**
     * Test of getLocalization method, of class Car.
     */
    @Test
    public void testGetLocalization() {
        System.out.println("getLocalization");
        assertThat(newCar.getLocalization()).isEqualTo("Katowice");
    }

    /**
     * Test of setId method, of class Car.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        newCar.setId("nowy");
        assertThat(newCar.getId()).isEqualTo("nowy");
    }

    /**
     * Test of setModel method, of class Car.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        newCar.setModel("nowy");
        assertThat(newCar.getModel()).isEqualTo("nowy");
    }

    /**
     * Test of setBrand method, of class Car.
     */
    @Test
    public void testSetBrand() {
        System.out.println("setBrand");
        newCar.setBrand("nowy");
        assertThat(newCar.getBrand()).isEqualTo("nowy");
    }

    /**
     * Test of setPricePerDay method, of class Car.
     */
    @Test
    public void testSetPricePerDay() {
        System.out.println("setPricePerDay");
        newCar.setPricePerDay(960);
        assertThat(newCar.getPricePerDay()).isEqualTo(960);
    }

    /**
     * Test of setNumberOfSeats method, of class Car.
     */
    @Test
    public void testSetNumberOfSeats() {
        System.out.println("setNumberOfSeats");
        newCar.setNumberOfSeats(2);
        assertThat(newCar.getNumberOfSeats()).isEqualTo(2);
    }

    /**
     * Test of setDailyKilometerLimit method, of class Car.
     */
    @Test
    public void testSetDailyKilometerLimit() {
        System.out.println("setDailyKilometerLimit");
        newCar.setDailyKilometerLimit(25);
        assertThat(newCar.getDailyKilometerLimit()).isEqualTo(25);
    }

    /**
     * Test of setGearboxType method, of class Car.
     */
    @Test
    public void testSetGearboxType() {
        System.out.println("setGearboxType");
        newCar.setGearboxType("automatyczna");
        assertThat(newCar.getGearboxType()).isEqualTo("automatyczna");
    }

    /**
     * Test of setAirConditioning method, of class Car.
     */
    @Test
    public void testSetAirConditioning() {
        System.out.println("setAirConditioning");
        newCar.setAirConditioning(false);
        assertThat(newCar.isAirConditioning()).isEqualTo(false);
    }

    /**
     * Test of setMileage method, of class Car.
     */
    @Test
    public void testSetMileage() {
        System.out.println("setMileage");
        newCar.setMileage(23);
        assertThat(newCar.getMileage()).isEqualTo(23);
    }

    /**
     * Test of setCategory method, of class Car.
     */
    @Test
    public void testSetCategory() {
        System.out.println("setCategory");
        newCar.setCategory("SUV");
        assertThat(newCar.getCategory()).isEqualTo("SUV");
    }

    /**
     * Test of setLocalization method, of class Car.
     */
    @Test
    public void testSetLocalization() {
        System.out.println("setLocalization");
        newCar.setLocalization("Warszawa");
        assertThat(newCar.getLocalization()).isEqualTo("Warszawa");
    }
    
}
