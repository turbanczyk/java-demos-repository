/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import javax.servlet.http.HttpServletRequest;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 * OrderCarController is controller responsible for correct order car process.
 * 
 * @author tomeku
 */
@Controller
public class OrderCarController {
    
    
    @PostMapping("/order")
    public String orderPage(Model model, HttpServletRequest req, @ModelAttribute("carOrder") OrderCar orderCar) {
        
        //set car id to order
        String carId = req.getParameter("carId");
        orderCar.setCarId(carId);
        
        return "order";
    }
    
    @GetMapping("/orderconfirmation")
    public String orderConfirmationPage(Model model, 
            @ModelAttribute("carOrder") OrderCar orderCar, Authentication authentication) {
        
        //przed użyciem sprawdzić czy a != null
        authentication.getName();
        
        return "order";
    }
    
    
}
