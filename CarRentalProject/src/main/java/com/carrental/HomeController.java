/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Controller.java to edit this template
 */
package com.carrental;

import com.carrental.data.CarRepository;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import java.time.LocalDate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author tomeku
 */
@Controller
public class HomeController {
    
    private JdbcTemplate jdbc;
    
    
    @Autowired
    public HomeController(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
    
    @GetMapping("/") 
    public String home(Model model) {
        
        List<String> listOfCities = null;
        listOfCities = jdbc.query("select localization from Car", this::mapRowToString);
        
        //remove duplicates
        List<String> cleanedListOfCities = removeDuplicates(listOfCities);
        //sorting 
        Collections.sort(cleanedListOfCities);

        //add to model
        model.addAttribute("cities", cleanedListOfCities);
        
        //set time in input, calendar fields
        LocalDate date = LocalDate.now();
        model.addAttribute("date", date.toString());
        
        return "home";
    }
    
    private String mapRowToString(ResultSet rs, int rowNum) throws SQLException {
        return new String(rs.getString("localization"));
    }
    
    private List<String> removeDuplicates(List<String> list) {
        
        List<String> newList = list.stream().distinct().collect(Collectors.toList());
        
        return newList;
    }
}