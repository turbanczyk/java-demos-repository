/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.security;

import com.carrental.entity.User;
import com.carrental.data.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * UserRepositoryUserDetailsService is the class which allow an application to 
 * process with user details services.
 * 
 * A UserRepositoryUserDetailsService implements UserDetailsService and is
 * a part of spring security implementation.
 * 
 * @author tomeku
 */
@Service
public class UserRepositoryUserDetailsService implements UserDetailsService {
    
    private UserRepository userRepository;
    
    @Autowired
    public UserRepositoryUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user != null) {
          return user;
        }
        throw new UsernameNotFoundException(
                        "Użytkownik ‘" + username + "’ nie został znaleziony.");
        }

}
