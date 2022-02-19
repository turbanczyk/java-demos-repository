package com.carrental;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.context.annotation.ComponentScan;
/**
 * CarRentalProjectApplication is the class which start application.
 * 
 * @author tomeku
 */
@SpringBootApplication
@ComponentScan
public class CarRentalProjectApplication {

    public static void main(String[] args) {
            SpringApplication.run(CarRentalProjectApplication.class, args);
    }

}
