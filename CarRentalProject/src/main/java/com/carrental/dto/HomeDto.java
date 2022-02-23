/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.dto;

import java.util.List;

import lombok.Setter;
import lombok.Getter;
import lombok.Builder;

/**
 * HomeDto class work as data transfer object in HomeController.
 * 
 * @author tomeku
 */
@Getter
@Setter
@Builder
public class HomeDto {
    
    private List<String> listOfCities;
    private String date;
}
