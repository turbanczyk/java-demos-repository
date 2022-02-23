/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental.controller;

import com.carrental.service.BookService;
import com.carrental.entity.Car;
import com.carrental.entity.OrderCar;
import com.carrental.dto.BookFormDto;
import com.carrental.dto.FilterFormDto;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.util.List;

/**
 * BookController accepts input and converts it to commands for the model or view
 * responsible for booking process.
 * 
 * @author tomeku
 */
@Controller
@SessionAttributes("carOrder")
public class BookController {
    
    private List<Car> carList;
    
    @Autowired
    private BookService bookService;

    @PostMapping("/book")
    public String bookPage(BookFormDto bookFormDto, Model model, 
            @ModelAttribute("carOrder") OrderCar orderCar) {
        
        carList = bookService.getCarList(bookFormDto);
        
        //set session atribute
        orderCar.setStartDate(BookService.stringToLocalDate(bookFormDto.getRentStart()));
        orderCar.setEndDate(BookService.stringToLocalDate(bookFormDto.getRentEnd()));
            
        //set attributes to model
        if(!carList.isEmpty()) {
            model.addAttribute("filterCarByPriceMin", Car.findWithMinPrice(carList).getPricePerDay());
            model.addAttribute("filterCarByPriceMax", Car.findWithMaxPrice(carList).getPricePerDay());
        } else {
            model.addAttribute("filterCarByPriceMin", 0);
            model.addAttribute("filterCarByPriceMax", 0);
        }
        model.addAttribute("carList", carList);
 
        return "book";
    }
    
    @PostMapping("/bookfilter")
    public String bookPageFiltered(FilterFormDto filterFormDto, Model model) {
          
        carList = bookService.filterCarList(filterFormDto, carList);
        
        //set atributes to model
        if(!carList.isEmpty()) {
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
    
}
