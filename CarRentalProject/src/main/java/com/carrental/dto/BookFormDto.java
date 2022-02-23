/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;
import lombok.AllArgsConstructor;

/**
 * BookFormDto class works as data transfer object in BookController.
 * 
 * @author tomeku
 */
@Getter
@Setter
@AllArgsConstructor
public class BookFormDto {
    
    String localization;
    String rentStart;
    String rentEnd;
}
