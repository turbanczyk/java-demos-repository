/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import com.carrental.data.UserRepository;
import com.carrental.security.RegistrationForm;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * RegistrationController is the controller responsible for correct user
 * registration process (for new users).
 * 
 * @author tomeku
 */
@Controller
public class RegistrationController {

    private UserRepository userRepo;
    private PasswordEncoder passwordEncoder;
    
    public RegistrationController(UserRepository userRepo, 
            PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }
    
    @GetMapping("/register")
    public String registerForm() {
        return "registration";
    }
    
    @PostMapping("/register")
    public String processRegistration(RegistrationForm form) {
        userRepo.save(form.toUser(passwordEncoder));
        return "redirect:/login";
    }
}