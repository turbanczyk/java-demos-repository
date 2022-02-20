/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

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
            //Car c = carRepository.findById(o.getCarId()).get();
 
            carList.add(carRepository.findById(o.getCarId()).get());
        }
        
        model.addAttribute("userOrders", userOrders);
        model.addAttribute("carList", carList);
        
        return "ordermanagement";
    }
    
}
