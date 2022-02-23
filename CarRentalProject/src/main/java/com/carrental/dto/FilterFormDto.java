/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author tomeku
 */
@Getter
@Setter
@AllArgsConstructor
public class FilterFormDto {
    
    private String filterCarCategory;
    private String filterCarByPrice;
}
