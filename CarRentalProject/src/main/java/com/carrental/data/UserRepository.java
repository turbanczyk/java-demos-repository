/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/springframework/Repository.java to edit this template
 */
package com.carrental.data;

import com.carrental.User;

import org.springframework.data.repository.CrudRepository;

/**
 * UserRepository is the interface which allow application to proceed operation
 * in database with user.
 * 
 * Interface extends CrudRepository and works with spring data.
 * 
 * @author tomeku
 */
public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
}
