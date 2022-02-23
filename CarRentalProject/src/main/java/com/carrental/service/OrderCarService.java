/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.carrental.service;

import com.carrental.data.CarOrderRepository;
import com.carrental.data.CarRepository;
import com.carrental.data.UserRepository;
import com.carrental.entity.Car;
import com.carrental.entity.OrderCar;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * OrderCarService is the class which allow an application to proceed with
 * order process.
 * 
 * @author tomeku
 */
@Service
@Getter
@Setter
@NoArgsConstructor
public class OrderCarService {
    
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarOrderRepository carOrderRepository;
    @Autowired
    private UserRepository userRepository;
    
    /**
     * The method is responsible for calculate total price of rent
     * @param orderCar OrderCar object with information about order
     * @return Return total price for order calculated based on information from
     * orderCar parameter (duration of rental, price per one day)
     */
    public double calculateTotalPrice(OrderCar orderCar) {
        long amountOfDays = orderCar.getEndDate().toEpochDay() - orderCar.getStartDate().toEpochDay()+1;
        double pricePerDay = carRepository.findById(orderCar.getCarId()).get().getPricePerDay();
        return amountOfDays * pricePerDay;
    }
    
    /**
     * The method responsible for return Car object specified by id in OrderCar object
     * @param orderCar OrderCar object
     * @return Return Car object specified in OrderCar
     */
    public Car getCar(OrderCar orderCar) {
        return carRepository.findById(orderCar.getCarId()).get();
    }
    
    /**
     * The method responsible for save order to database.
     * @param orderCar OrderCar object which should be save.
     * @param userName User name
     * @return Return true if operation is positive.
     */
    public boolean saveOrderCar(OrderCar orderCar, String userName) {
        if(orderCar.getStartDate() != null && orderCar.getEndDate() != null
                && !orderCar.getCarId().isBlank() &&
                orderCar.getTotalPrice() > 0) {
            
            orderCar.setUserId(userRepository.findByUsername(userName).getId());
            orderCar.setPlacedAt(LocalDate.now());

            carOrderRepository.save(orderCar);
            
            return true;
        } else {
            return false;
        }
    }
}
