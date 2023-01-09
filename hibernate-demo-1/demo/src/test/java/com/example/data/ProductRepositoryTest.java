package com.example.data;

import com.example.entities.Product;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import java.util.List;

import static org.junit.Assert.*;

public class ProductRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ProductRepository productRepository;

    public ProductRepositoryTest() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Product");
        entityManager = entityManagerFactory.createEntityManager();
        productRepository = new ProductRepository(entityManager);
    }

    @Test
    public void save() {
        Product p = new Product(4, "pen");
        productRepository.save(p);
        assertTrue(productRepository.findAll().size() == 4);
        assertTrue(productRepository.findById(4).getName().equals("pen"));
        //clean -> set table to previous state
        productRepository.remove(p);
    }

    @Test
    public void findAll() {
        List<Product> productList = productRepository.findAll();
        assertTrue(productList.size() == 3);
        assertTrue(productList.get(1).getName().equals("paper"));
    }

    @Test
    public void findById() {
        Product p = productRepository.findById(3);
        assertTrue(p.getName().equals("stapler"));

        Product p2 = productRepository.findById(1);
        assertTrue(p2.getName().equals("pencil"));
    }

    @Test
    public void remove() {
        Product p = new Product(3, "stapler");
        productRepository.remove(p);
        assertTrue(productRepository.findAll().size() == 2);

        //clean -> set table to previous state
        productRepository.save(p);
        assertTrue(productRepository.findAll().size() == 3);
    }
}