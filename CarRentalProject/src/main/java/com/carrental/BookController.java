/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;
import com.carrental.data.CarOrderRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author tomeku
 */
@Controller
@SessionAttributes("carOrder")
public class BookController {
    
    private CarRepository carRepository;
    private CarOrderRepository carOrderRepository;
    
    @Autowired
    public BookController(CarRepository carRepository, CarOrderRepository carOrderRepository) {
        this.carRepository = carRepository;
        this.carOrderRepository = carOrderRepository;
    }
    
    @PostMapping("/book")
    public String bookPage(HttpServletRequest req, Model model, @ModelAttribute OrderCar orderCar) {
        
        //take data
        String localization = req.getParameter("localization");
        LocalDate rentStartDate = stringToLocalDate(req.getParameter("rentStart"));
        LocalDate rentEndDate = stringToLocalDate(req.getParameter("rentEnd"));
        
        //find available cars
        TimePeriod bookTimePeriod = new TimePeriod(rentStartDate, rentEndDate);
        BookAssistant bookAssistant = new BookAssistant(carRepository, carOrderRepository);
        List<Car> carList = 
                bookAssistant.getCarsAvailableInTimePeriodAndLocalization(bookTimePeriod, localization);
        
        //set data to session object
        orderCar.setStartDate(rentStartDate);
        orderCar.setEndDate(rentEndDate);
        
        model.addAttribute("carList", carList);
        
        return "book";
    }
    
    @ModelAttribute("carOrder")
    public OrderCar orderCar() {
        return new OrderCar();
    }
    
    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        
        return LocalDate.parse(date, formatter);
    }
    
}
