/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.service;

import com.carrental.entity.Car;
import com.carrental.entity.OrderCar;
import com.carrental.dto.TimePeriod;
import com.carrental.data.CarRepository;
import com.carrental.data.CarOrderRepository;
import com.carrental.dto.BookFormDto;
import com.carrental.dto.FilterFormDto;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import java.util.List;
import java.util.ArrayList;
import java.util.HashSet;
import lombok.AllArgsConstructor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * BookService is the class which allow an application to proceed with
 * informations about availability of cars.
 * 
 * 
 * @author tomeku
 */
@Service
@Getter
@Setter
@NoArgsConstructor
public class BookService {
    
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarOrderRepository carOrderRepository;
    
    /**
     * The method used to find that specified time period (checking time period)
     * is out of another time period (basic time period)
     * @param checkingPeriod Parameter of checking time period
     * @param basicPeriod Parameter of basic time period
     * @return 
     */
    public boolean isTimePeriodOutOfTimePeriod(TimePeriod checkingPeriod, TimePeriod basicPeriod) {
        
        if (checkingPeriod.getEndDate().isBefore(basicPeriod.getStartDate()) ||
                checkingPeriod.getStartDate().isAfter(basicPeriod.getEndDate())) {
            return true;
        } else {
            return false;
        }
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
    
    /**
     * The method return list of Car based on criteria delivered by BookFormDto
     * @param bookFormDto Dto with criterias
     * @return Return list of Car objects
     */
    public List<Car> getCarList(BookFormDto bookFormDto) {
        TimePeriod bookTimePeriod = new TimePeriod(stringToLocalDate(bookFormDto.getRentStart()), 
                stringToLocalDate(bookFormDto.getRentEnd()));
        return getCarsAvailableInTimePeriodAndLocalization(bookTimePeriod, bookFormDto.getLocalization());
    }
    
    /**
     * The method return list of Car object based on criteria set in FilterFOrmDto object
     * @param filterFormDto Dto with criterias
     * @param carList List of car which should be filtered based on criteria delivered in Dto
     * @return Return filtered list of Car objects
     */
    public List<Car> filterCarList(FilterFormDto filterFormDto, List<Car> carList) {

        carList = Car.findByCategory(carList, filterFormDto.getFilterCarCategory());
        carList = Car.findWithPriceLowerOrEqual(carList, Double.parseDouble(filterFormDto.getFilterCarByPrice()));
        
        return carList;
    }
    
    /**
     * The method responsible for convert Strind data in format yyyy-MM-dd to object LocalDate
     * @param date Date as a String in format yyyy-MM-dd
     * @return Date converted to object type LocalDate
     */
    public static LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        
        return LocalDate.parse(date, formatter);
    }
}
