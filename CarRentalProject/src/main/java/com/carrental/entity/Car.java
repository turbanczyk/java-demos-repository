/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.entity;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AccessLevel;

/**
 * Car is the class which allow an application to agregate information about
 * single car.
 * 
 * A Car object encapsulate main information about car like id, model, brand,
 * price per day, number of seats, gearbox type, air conditioning system,
 * mileage, category and localization
 * 
 * @author tomeku
 */

@Getter
@Setter
@NoArgsConstructor(access=AccessLevel.PRIVATE, force=true)
@Entity
public class Car {
    
    //@Id
    @javax.persistence.Id
    @org.springframework.data.annotation.Id
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
    
    /**
     * Class constructor with parameters.
     * @param model Parameter set car model name
     * @param brand Parameter set car brand name
     * @param pricePerDay Parameter set price for 1 day rental
     * @param numberOfSeats Parameter set number of seats in car
     * @param dailyKilometerLimit Parameter set daily kilometer limit for car
     * @param gearboxType Parameter set gearbox type (permissible values: manualna,
     * automatyczna
     * @param airConditioning Paramter set information about air conditioning system
     * (if system is available should be set true value)
     * @param mileage Parameter set mileage
     * @param category Parameter set category of car (permissible values: małe,
     * średnie, duże, kombi, premium, minivany, SUV)
     * @param localization Parameter set localization of car
     */
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
    
    public static List<Car> findByCategory(List<Car> carList, String category) {
        ArrayList<Car> filteredList = new ArrayList<>();
        for(Car i : carList) {
            if(i.getCategory().equals(category)) {
                filteredList.add(i);
            }
        }
        
        return filteredList;
    }
    
    public static Car findWithMaxPrice(List<Car> carList) {
        Car car = carList.get(0);
        
        for(Car i : carList) {
            if(i.getPricePerDay() > car.getPricePerDay()) {
                car = i;
            }
        }
        return car;
    }
    
    public static Car findWithMinPrice(List<Car> carList) {  
        Car car = carList.get(0);
        
        for(Car i : carList) {
            if(i.getPricePerDay() < car.getPricePerDay()) {
                car = i;
            }
        }
        return car;
    }
    
    public static List<Car> findWithPriceLowerOrEqual(List<Car> carList, double price) {
        ArrayList<Car> filteredList = new ArrayList<>();
        
        for(Car i : carList) {
            if(i.getPricePerDay() == price || i.getPricePerDay() < price) {
                filteredList.add(i);
            }
        }
        
        return filteredList;
    }

}
