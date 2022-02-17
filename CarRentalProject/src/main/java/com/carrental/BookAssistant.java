/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;
import com.carrental.data.CarOrderRepository;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * BookAssistant is the class which allow an application to proceed with
 * informations about availability of cars.
 * 
 * BookAssistant object is mainly responsible for process of finding available
 * cars and other necesseary during this things.
 * 
 * @author tomeku
 */

@Getter
@Setter
@NoArgsConstructor
public class BookAssistant {
    
    private CarRepository carRepository;
    private CarOrderRepository carOrderRepository;
    
    /**
     * Class constructor with two parameters.
     * @param carRepository Object of class CarRepository
     * @param carOrderRepository Object of class CarOrderRepository
     */
    @Autowired
    public BookAssistant(CarRepository carRepository, 
            CarOrderRepository carOrderRepository) {
        this.carRepository = carRepository;
        this.carOrderRepository = carOrderRepository;
    }
    
    /**
     * The method used to find that specified time period (checking time period)
     * is out of another time period (basic time period)
     * @param checkingPeriod Parameter of checking time period
     * @param basicPeriod Parameter of basic time period
     * @return 
     */
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
    
    /**
     * The method is used to find cars (object of class Car) which are available
     * in specified time period and in specified localization
     * @param timePeriod Parameter determines time period in which car should
     * be available
     * @param localization Parameter determines location in which car should be
     * available
     * @return Return list of cars (list of objects Car)
     */
    public List<Car> getCarsAvailableInTimePeriodAndLocalization(TimePeriod timePeriod, 
            String localization) {
        
        //step 1 - get cars with correct localization
        List<Car> carsList = carRepository.findByLocalizationIs(localization);
        
        //step 2 - exclude cars witch are booked
        List<OrderCar> carOrder = carOrderRepository.findAll();
        List<String> excludedCarId = new ArrayList<String>();
        for(OrderCar i : carOrder) {
            if(!isTimePeriodOutOfTimePeriod(timePeriod, new TimePeriod(i.getStartDate(), i.getEndDate()))) {
                excludedCarId.add(i.getCarId());
            }
        }
        //removes duplicates
        excludedCarId = new ArrayList<>(new HashSet<>(excludedCarId));
 
        //step 3 - exclude from cars cars with id from list from point 2.2
        for(int i = 0; i < carsList.size(); i++) {
            boolean removed = false;
            
            for(int j = 0; j < excludedCarId.size() && !removed; j++) {
                if(carsList.get(i).getId().equals(excludedCarId.get(j))) {
                    carsList.remove(i);
                    //one step back because remove from List
                    i--;
                    //quit the loop
                    removed = true;
                }
            }
        }
        
        return carsList;
    }
}
