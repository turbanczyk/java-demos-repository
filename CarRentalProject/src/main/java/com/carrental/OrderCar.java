/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import java.time.LocalDate;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

/**
 * OrderCar is the class which allow application to agregate information about
 * client order.
 * 
 * A OrderCar object encapsulate information aobut order like order id, user id,
 * rental start date, rental end date, date of order.
 * 
 * @author tomeku
 */
@Getter
@Setter
@Entity
public class OrderCar {
    
    @Id
    private String id;
    private String userId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDate placedAt;
    
    /**
     * Constructor with parameters.
     * @param userId Parameter set user id which ordered car
     * @param carId Parameter set car id which is ordered
     * @param startDate Parameter set start of rental period
     * @param endDate Parameter set end of rental period
     * @param placedAt Parameter set date of rental order
     */
    public OrderCar(String userId, String carId, LocalDate startDate,
            LocalDate endDate, LocalDate placedAt) {
        
        Preconditions.checkArgument(!Strings.isNullOrEmpty(userId), 
                "User ID can't be empty or null");
        Preconditions.checkArgument(!Strings.isNullOrEmpty(carId), 
                "Car ID can't be empty or null");
        Preconditions.checkArgument(startDate != null, 
                "Start date can't be null");
        Preconditions.checkArgument(endDate != null, 
                "End date can't be null");
        Preconditions.checkArgument(!startDate.isAfter(endDate), 
                "Start date can't be after end date"); 
        Preconditions.checkArgument(!endDate.isBefore(startDate), 
                "End date can't be before start date");
        Preconditions.checkArgument(placedAt != null, 
                "Placed at can't be null");
        
        this.id = UUID.randomUUID().toString();
        this.userId = userId;
        this.carId = carId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.placedAt = placedAt;
    }
    
    /**
     * No parameters constructor.
     */
    public OrderCar() {
        this.id = UUID.randomUUID().toString();
    }

}
