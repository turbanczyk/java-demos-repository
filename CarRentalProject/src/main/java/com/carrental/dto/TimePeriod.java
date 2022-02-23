/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.dto;

import com.google.common.base.Preconditions;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
        
/**
 * TimePeriod is the class which allow an application to agregate information
 * about time period (start date, end date).
 * 
 * @author tomeku
 */
@Getter
@Setter
public class TimePeriod {
    
    private LocalDate startDate;
    private LocalDate endDate;
    
    /**
     * Constructor with two parameters.
     * @param startDate Parameter set start date of time period
     * @param endDate Parameter set end date of time period
     */
    public TimePeriod(LocalDate startDate, LocalDate endDate) {
        Preconditions.checkArgument(startDate != null, 
                "Start date can't be null");
        Preconditions.checkArgument(endDate != null, 
                "End date can't be null");
        Preconditions.checkArgument(!startDate.isAfter(endDate), 
                "Start date can't be after end date");
        Preconditions.checkArgument(!endDate.isBefore(startDate), 
                "End date can't be before start date");
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
