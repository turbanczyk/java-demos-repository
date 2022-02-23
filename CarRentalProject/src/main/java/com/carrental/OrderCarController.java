/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import com.carrental.entity.OrderCar;
import com.carrental.entity.Car;
import com.carrental.data.CarOrderRepository;
import com.carrental.data.UserRepository;
import com.carrental.data.CarRepository;

import java.time.LocalDate;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 * OrderCarController is controller responsible for correct order car process.
 * 
 * @author tomeku
 */
@Controller
@SessionAttributes("carOrder")
public class OrderCarController {
    
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CarRepository carRepository;
    @Autowired
    private CarOrderRepository carOrderRepository;
    
    /*
    @Autowired
    public OrderCarController(UserRepository userRepository, CarRepository carRepository) {
        this.userRepository = userRepository;
        this.carRepository = carRepository;
    }
    */
    
    @PostMapping("/order")
    public String orderPage(Model model, HttpServletRequest req, 
            @ModelAttribute("carOrder") OrderCar orderCar) {
        
        //set car id to order
        String carId = req.getParameter("carId");
        orderCar.setCarId(carId);
        
        //set total price to order
        long amountOfDays = orderCar.getEndDate().toEpochDay() - orderCar.getStartDate().toEpochDay()+1;
        double pricePerDay = carRepository.findById(carId).get().getPricePerDay();
        orderCar.setTotalPrice(amountOfDays * pricePerDay);
        
        Car car = carRepository.findById(carId).get();
        
        //set attributes to model
        model.addAttribute("car", car);
        model.addAttribute("order", orderCar);
        
        return "order";
    }
    
    @GetMapping("/orderconfirmation")
    public String orderConfirmationPage(Model model, 
            @ModelAttribute("carOrder") OrderCar orderCar, Authentication authentication) {
        
        //check all parameters
        if(orderCar.getStartDate() != null && orderCar.getEndDate() != null
                && !orderCar.getCarId().isBlank() && authentication != null &&
                orderCar.getTotalPrice() > 0) {
            
            orderCar.setUserId(userRepository.findByUsername(authentication.getName()).getId());
            orderCar.setPlacedAt(LocalDate.now());

            carOrderRepository.save(orderCar);
            
            model.addAttribute("isOrdered", true);
            
        } else {
            model.addAttribute("isOrdered", false);
        }
        
        return "orderconfirmation";
    }
      
}
