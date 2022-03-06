/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/RestController.java to edit this template
 */
package com.carrental.controller;

import com.carrental.dto.BookFormDto;
import com.carrental.entity.Car;
import com.carrental.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * APIController is controller responsible for provide api services.
 * 
 * @author tomeku
 */
@RestController
@RequestMapping("/api")
public class APIController {
    
    @Autowired
    private BookService bookService;
    
    @GetMapping(value = "/carlist/{localization}/{rentStart}/{rentEnd}", produces = "application/json")
    public ResponseEntity<List<Car>> getCarList(BookFormDto bookFormDto) {
        return new ResponseEntity<>(bookService.getCarList(bookFormDto), HttpStatus.OK);
    }
}
