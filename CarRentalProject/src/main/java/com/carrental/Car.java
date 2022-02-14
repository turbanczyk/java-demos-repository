/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.AccessLevel;
import lombok.Data;

/**
 *
 * @author tomeku
 */

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Car {
    
    @Id
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
        
        Preconditions.checkArgument(!Strings.isNullOrEmpty(model), 
                "Car model can't be empty or null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(brand), 
                "Car brand can't be empty or null");
        Preconditions.checkArgument(pricePerDay > 0, 
                "Price per day can't be below 0");
        Preconditions.checkArgument(numberOfSeats > 0 && numberOfSeats < 11, 
                "Number of seats can't be smaller than 0 and bigger than 10");
        Preconditions.checkArgument(dailyKilometerLimit > 0, 
                "Daily kilometer limit can't be below 0");
        Preconditions.checkArgument(gearboxType.equals("automatyczna") ||
                gearboxType.equals("manualna"), 
                "Gearbox type can be only manualna lub automatyczna");
        Preconditions.checkArgument(mileage > 0, "Mileage can't be below 0");
        Preconditions.checkArgument(category.equals("małe") ||
                category.equals("średnie") || category.equals("duże") ||
                category.equals("kombi") || category.equals("premium") ||
                category.equals("minivany") || category.equals("SUV"),
                "Category can be only: małe, średnie, duże, kombi, premium, minivany, SUV");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(localization), 
                "Localization can't be empty or null");

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
