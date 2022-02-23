/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import com.carrental.entity.OrderCar;
import com.carrental.entity.Car;
import com.carrental.data.CarOrderRepository;
import com.carrental.data.CarRepository;
import com.carrental.data.UserRepository;

import java.util.List;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * OrderManagementController accepts input and converts it to commands for the model or view
 * responsible for user order management.
 * 
 * @author tomeku
 */
@Controller
public class OrderManagementController {
    
    @Autowired
    private CarOrderRepository carOrderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    
    @GetMapping("/ordermanagement")
    public String orderManagementPage(Model model, Authentication authentication) {
        
        double userId = userRepository.findByUsername(authentication.getName()).getId();
        List<OrderCar> userOrders = carOrderRepository.findByUserId(userId);
        
        List<Car> carList = new ArrayList<>();
        for (OrderCar o: userOrders) {
 
            carList.add(carRepository.findById(o.getCarId()).get());
        }
        
        model.addAttribute("userOrders", userOrders);
        model.addAttribute("carList", carList);
        
        return "ordermanagement";
    }
    
}
