/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

/**
 *
 * @author tomeku
 */
@Controller
public class OrderCarController {
    
    @PostMapping("/order")
    public String orderPage(Model model, HttpServletRequest req, @ModelAttribute("carOrder") OrderCar orderCar) {
        
        String carId = req.getParameter("carId");
        //System.out.println(orderCar.getId());
        System.out.println(carId);
        
        return "order";
    }
    
}
