/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;

import java.util.List;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
/**
 *
 * @author tomeku
 */
       
@Getter
@Setter
@NoArgsConstructor
//@Service
public class BookAssistant {
    
    //@Autowired
    private CarRepository carRepository;
    
    @Autowired
    public BookAssistant(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    
    //public BookAssistant() { }
    
    public boolean isTimePeriodOutOfTimePeriod(TimePeriod checkingPeriod, TimePeriod basicPeriod) {
        boolean i = false;

        //check left site of basic period
        if (checkingPeriod.getEndDate().isBefore(basicPeriod.getStartDate())) {
            i = true;
        }
        
        //check right site of basic period
        if (checkingPeriod.getStartDate().isAfter(basicPeriod.getEndDate())) {
            i = true;
        }
        
        return i;
    }
    
    public List<Car> getCarsAvailableInTimePeriodAndLocalization(TimePeriod timePeriod, 
            String localization) {
        
        
        return null;
    }
}
