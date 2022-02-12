/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import java.util.UUID;

import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tomeku
 */
public class Car {
    
    private String id;
    private String model;
    private String brand;
    private double pricePerDay;
    private int numberOfSeats;
    private int dailyKilometerLimit;
    private String gearboxType;
    private boolean airConditioning;
    private int mileage;
    private String category;
    private String localization;
    
    public Car(String model, String brand, double pricePerDay,
            int numberOfSeats, int dailyKilometerLimit, String gearboxType,
            boolean airConditioning, int mileage, String category,
            String localization) {
        //dodać walidatory
        this.id = UUID.randomUUID().toString();
        this.model = model;
        this.brand = brand;
        this.pricePerDay = pricePerDay;
        this.numberOfSeats = numberOfSeats;
        this.dailyKilometerLimit = dailyKilometerLimit;
        this.gearboxType = gearboxType;
        this.airConditioning = airConditioning;
        this.mileage = mileage;
        this.category = category;
        this.localization = localization;
    }
}
