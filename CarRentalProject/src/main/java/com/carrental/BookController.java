/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tomeku
 */
@Controller
public class BookController {
    
    private CarRepository carRepository;
    
    @Autowired
    public BookController(CarRepository carRepository) {
        this.carRepository = carRepository;
    }
    
    @PostMapping("/book")
    public String page(HttpServletRequest req, Model m) {
        
        //take data
        String localization = req.getParameter("localization");
        LocalDate rentStartDate = stringToLocalDate(req.getParameter("rentStart"));
        LocalDate rentEndDate = stringToLocalDate(req.getParameter("rentEnd"));
        
        //find available cars
        TimePeriod bookTimePeriod = new TimePeriod(rentStartDate, rentEndDate);
        BookAssistant bookAssistant = new BookAssistant(carRepository);
        List<Car> carList = 
                bookAssistant.getCarsAvailableInTimePeriodAndLocalization(bookTimePeriod, localization);
        
        return "book";
    }
    
    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        
        return LocalDate.parse(date, formatter);
    }
    
}
