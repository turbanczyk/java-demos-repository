/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Service.java to edit this template
 */
package com.carrental.service;

import com.carrental.dto.HomeDto;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * HomeService is the class which allow an application to proceed with
 * informations from received from HomeController.
 * @author tomeku
 */
@Service
@Data
@NoArgsConstructor
public class HomeService {
    
    @Autowired
    private JdbcTemplate jdbc; 
    private List<String> listOfCities;
    private String date;
    
    /**
     * The method responsible for return dto object HomeDto which contains information
     * necesseary to proceed home page.
     * @return Dto object HomeDto
     */
    public HomeDto getHome() {
        listOfCities = jdbc.query("select localization from Car", this::mapRowToString);
        //remove duplicates
        //List<String> cleanedListOfCities = removeDuplicates(listOfCities);
        listOfCities = removeDuplicates(listOfCities);
        //sorting 
        Collections.sort(listOfCities);
        
        HomeDto homeDto = HomeDto.builder()
                .listOfCities(listOfCities)
                .date(LocalDate.now().toString())
                .build();
        
        return homeDto;
    }
    
    /**
     * The method responsible for proceeding with results of jdbc query
     * @param rs ResultSet object
     * @param rowNum Row number
     * @return Return String
     * @throws SQLException Exception
     */
    private String mapRowToString(ResultSet rs, int rowNum) throws SQLException {
        return new String(rs.getString("localization"));
    }
    
    /**
     * The metody responsible for remove duplicates from List
     * @param list List which should be proceed
     * @return Return list without duplicates
     */
    private List<String> removeDuplicates(List<String> list) {
        
        List<String> newList = list.stream().distinct().collect(Collectors.toList());
        
        return newList;
    }
}
