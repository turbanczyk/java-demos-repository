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
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

/**
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
    
    public double calculateTotalPrice(OrderCar orderCar) {
        long amountOfDays = orderCar.getEndDate().toEpochDay() - orderCar.getStartDate().toEpochDay()+1;
        double pricePerDay = carRepository.findById(orderCar.getCarId()).get().getPricePerDay();
        return amountOfDays * pricePerDay;
    }
    
    public Car getCar(OrderCar orderCar) {
        return carRepository.findById(orderCar.getCarId()).get();
    }
    
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
