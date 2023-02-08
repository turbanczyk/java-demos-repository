package com.example.springbootbackend.repository;

import com.example.springbootbackend.entity.Productline;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductlineRepository extends JpaRepository<Productline, String> {
}