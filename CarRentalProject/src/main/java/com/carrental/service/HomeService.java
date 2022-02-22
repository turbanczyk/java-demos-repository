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
 *
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
    
    private String mapRowToString(ResultSet rs, int rowNum) throws SQLException {
        return new String(rs.getString("localization"));
    }
    
    private List<String> removeDuplicates(List<String> list) {
        
        List<String> newList = list.stream().distinct().collect(Collectors.toList());
        
        return newList;
    }
}
