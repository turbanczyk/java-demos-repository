/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.carrental.data;

import java.util.List;
import java.time.LocalDate;

import com.carrental.CarOrder;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author tomeku
 */
public interface CarOrderRepository extends CrudRepository<CarOrder, String> {
    //List<CarOrder> findCarOrderIsAfterStartDate(LocalDate date);
    List<CarOrder> findByStartDateIsAfter(LocalDate date);
}
