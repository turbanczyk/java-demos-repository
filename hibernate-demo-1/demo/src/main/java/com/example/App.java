package com.example;

import com.example.data.ProductRepository;
import com.example.entities.Product;

import javax.persistence.Persistence;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Product");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        
        // Create repository
        ProductRepository productRepository = new ProductRepository(entityManager);
        // Take all from database and print on standard output
        List<Product> productList = productRepository.findAll();
        for(Product p : productList){
            System.out.println(p.getName());
        }

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
        
    }
}
