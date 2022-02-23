/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.carrental.data;

import com.carrental.entity.Car;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CarRepository is the interface which allow application to proceed operation
 * in database with cars.
 * 
 * @author tomeku
 */
@Repository
public interface CarRepository extends CrudRepository<Car, String> {
    List<Car> findAll();
    List<Car> findByLocalizationIs(String localization);
}
