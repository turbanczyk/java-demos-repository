/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * LoginController accepts input and converts it to commands for the model or view
 * responsible for login process.
 * 
 * @author tomeku
 */
@Controller
public class LoginController {
    
    @GetMapping("/login")
    public String loginPage(Model model) {

        return "login";
    }
    
}
