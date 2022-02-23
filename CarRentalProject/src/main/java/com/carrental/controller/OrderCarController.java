/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import com.carrental.entity.OrderCar;
import com.carrental.service.OrderCarService;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * OrderCarController is controller responsible for correct order car process.
 * 
 * @author tomeku
 */
@Controller
@SessionAttributes("carOrder")
public class OrderCarController {
    
    @Autowired
    private OrderCarService orderCarService;
    
    @PostMapping("/order")
    public String orderPage(Model model, String carId, 
            @ModelAttribute("carOrder") OrderCar orderCar) {
        
        //set parameters to session variable
        orderCar.setCarId(carId);
        orderCar.setTotalPrice(orderCarService.calculateTotalPrice(orderCar));
        
        //set attributes to model
        model.addAttribute("car", orderCarService.getCar(orderCar));
        model.addAttribute("order", orderCar);
        
        return "order";
    }
    
    @GetMapping("/orderconfirmation")
    public String orderConfirmationPage(Model model, 
            @ModelAttribute("carOrder") OrderCar orderCar, Authentication authentication) {
        
        if(authentication.isAuthenticated() && orderCarService.saveOrderCar(orderCar, authentication.getName())) {   
            model.addAttribute("isOrdered", true);   
        } else {
            model.addAttribute("isOrdered", false);
        }
        
        return "orderconfirmation";
    }
      
}
