/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.security;

import com.carrental.entity.User;

import lombok.Data;

import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    
    private final String username;
    private final String password; 
    private final String name;
    private final String surname;
    private final String country;
    private final String city;
    private final String street;
    private final String drivingLicenseNumber;
    private final String email;
    private final String telephoneNumber;
    //private final String creditCardId;
  
  public User toUser(PasswordEncoder passwordEncoder) {
    return new User(
        username, passwordEncoder.encode(password), name, surname, country,
        city, street, drivingLicenseNumber, email, telephoneNumber);
  }
}