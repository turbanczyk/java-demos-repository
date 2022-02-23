/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.carrental.data;

import java.util.List;
import java.time.LocalDate;

import com.carrental.entity.OrderCar;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * CarOrderRepository is the interface which allow application to proceed operation
 * in database with orders.
 * 
 * @author tomeku
 */
@Repository
public interface CarOrderRepository extends CrudRepository<OrderCar, String> {
    List<OrderCar> findByStartDateIsAfter(LocalDate date);
    List<OrderCar> findAll();
    List<OrderCar> findByUserId(double userId);
}
