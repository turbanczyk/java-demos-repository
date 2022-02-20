/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;
import com.carrental.data.CarOrderRepository;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * BookController is controller responsible for correct car search operation.
 * 
 * @author tomeku
 */
@Controller
@SessionAttributes("carOrder")
public class BookController {
    
    private CarRepository carRepository;
    private CarOrderRepository carOrderRepository;
    private List<Car> carList;
    
    @Autowired
    public BookController(CarRepository carRepository, CarOrderRepository carOrderRepository) {
        this.carRepository = carRepository;
        this.carOrderRepository = carOrderRepository;
    }
    
    @PostMapping("/book")
    public String bookPage(HttpServletRequest req, Model model, @ModelAttribute("carOrder") OrderCar orderCar) {
        
        //first search, without filter available on webpage
        if(req.getParameter("localization") != null && req.getParameter("rentStart") != null &&
                req.getParameter("rentEnd") != null) {
            //take data
            String localization = req.getParameter("localization");
            LocalDate rentStartDate = stringToLocalDate(req.getParameter("rentStart"));
            LocalDate rentEndDate = stringToLocalDate(req.getParameter("rentEnd"));

            //find available cars
            TimePeriod bookTimePeriod = new TimePeriod(rentStartDate, rentEndDate);
            BookAssistant bookAssistant = new BookAssistant(carRepository, carOrderRepository);
            carList = bookAssistant.getCarsAvailableInTimePeriodAndLocalization(bookTimePeriod, localization);

            //set data to session object
            orderCar.setStartDate(rentStartDate);
            orderCar.setEndDate(rentEndDate);
        }
        //search with custom filters
        else {
            //filtr by category
            String carCategory = req.getParameter("filterCarCategory");
            carList = Car.findByCategory(carList, carCategory);
            
            //filtr by price
            if(req.getParameter("filterCarByPrice") != null) {
                carList = Car.findWithPriceLowerOrEqual(carList, Double.parseDouble(req.getParameter("filterCarByPrice")));
            }
        }
        
        if(carList.size() > 0) {
            model.addAttribute("filterCarByPriceMin", Car.findWithMinPrice(carList).getPricePerDay());
            model.addAttribute("filterCarByPriceMax", Car.findWithMaxPrice(carList).getPricePerDay());
        } else {
            model.addAttribute("filterCarByPriceMin", 0);
            model.addAttribute("filterCarByPriceMax", 0);
        }
        
        model.addAttribute("carList", carList);
 
        return "book";
    }
    
    /**
     * Method necessary for propoper injection of session attribute
     * @return Return object of class OrderCar
     */
    @ModelAttribute("carOrder")
    public OrderCar orderCar() {
        return new OrderCar();
    }
    
    private LocalDate stringToLocalDate(String date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-d");
        
        return LocalDate.parse(date, formatter);
    }
    
}
