/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import lombok.Getter;
import lombok.Setter;
/**
 *
 * @author tomeku
 */
       
@Getter
@Setter
public class BookAssistant {
    
    public BookAssistant() {
        
    }
    
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
}
