package com.example.data;

import com.example.entities.Product;

import java.util.List;

import javax.persistence.EntityManager;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ProductRepository {
    
    private EntityManager entityManager;

    public void save(Product product) {
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    public List<Product> findAll() {
        //native sql query
        //return entityManager.createNativeQuery("select * from product", Product.class).getResultList();
        //hibernate dialect query
        return entityManager.createQuery("from Product").getResultList();
    }

    public Product findById(int id) {
        Product product = entityManager.find(Product.class, id);
        return product;
    }

    public void remove(Product product) {
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
        entityManager.getTransaction().commit();
    }
}
