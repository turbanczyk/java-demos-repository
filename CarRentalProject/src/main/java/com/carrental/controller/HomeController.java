/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import com.carrental.service.HomeService;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

/**
 * HomeController is controller responsible for present home page of application.
 * 
 * @author tomeku
 */
@Controller
public class HomeController {
    
    @Autowired
    private HomeService homeService;
    
    @GetMapping("/") 
    public String home(Model model) {

        model.addAttribute("homeService", homeService.getHome());
        
        return "home";
    }
    
}