/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.carrental.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;

/**
 * ScurityConfig is the class which provides spring security configuration.
 * 
 * @author tomeku
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    
    /* 
    //for second method
    @Autowired
    DataSource dataSource;
    */
    
    //for third method
    @Autowired
    private UserRepositoryUserDetailsService userDetailsService;
    //private UserDetailsService userDetailsService;

    
    /*
    //First method of user set, configuration with setted users
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .inMemoryAuthentication()
          .withUser("user1")
            .password("{noop}password1")
            .authorities("ROLE_USER")
          .and()
          .withUser("user2")
            .password("{noop}password2")
            .authorities("ROLE_USER");
    }
    */
    
    /*
    //Second method of user set, configuration with jdbc. In database must be field enabled and authority
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .jdbcAuthentication()
          .dataSource(dataSource)
          .usersByUsernameQuery(
              "select username, password, enabled from User " +
              "where username=?")
          .authoritiesByUsernameQuery(
              "select username, authority from User " +
               "where username=?")
           //insecure, should be change in production version
           .passwordEncoder(NoOpPasswordEncoder.getInstance());
    }
    */
    
    //Third method of user set
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
        .userDetailsService(userDetailsService)
        .passwordEncoder(encoder());
    }
    
    
    @Override
    protected void configure(HttpSecurity http) throws Exception {
      http
        .authorizeRequests()
            .antMatchers("/orderconfirmation", "/ordermanagement").hasRole("USER")
            .antMatchers("/", "/**").permitAll()
        .and()
              .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/#");
     
    }

    @Bean
    public PasswordEncoder encoder() {
      //insecure, should be change in production version
      return NoOpPasswordEncoder.getInstance();
    }

}
