/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 *
 * @author tomeku
 */
@Controller
public class OrderCarController {
    
    @PostMapping("/order")
    public String orderPage(Model model) {
        
        
        return "order";
    }
    
}
