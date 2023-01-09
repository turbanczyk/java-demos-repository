package repositories;

import entities.Productline;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductlineRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ProductlineRepository productlineRepository;

    public ProductlineRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        productlineRepository = new ProductlineRepository(entityManager);
    }
    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Productline> productlineList = productlineRepository.findAll();
        assertTrue(productlineList.size() == 7);
    }

    @Test
    public void findById() {
        Productline productline = productlineRepository.findAll().get(new Random().nextInt(7));
        Productline p = productlineRepository.findById(productline.getId());
        assertTrue(p.getId().equals(productline.getId()));
        assertTrue(p.getTextDescription().equals(productline.getTextDescription()));
    }

    @Test
    public void save() {
        Productline p = new Productline("Product line id", "Text description", null, null);

        productlineRepository.save(p);
        assertTrue(productlineRepository.findAll().size() == 8);

        //clean
        productlineRepository.remove(p);
        assertTrue(productlineRepository.findAll().size() == 7);
    }

    @Test
    public void remove() {
        save();
    }

    @Test
    public void update() {
        Productline productline = productlineRepository.findAll().get(new Random().nextInt(7));
        Productline productline2 = productline;

        productline.setTextDescription("New description");
        productlineRepository.update(productline);
        assertTrue(productlineRepository.findById(productline.getId()).getTextDescription().equals("New description"));

        //clean
        productline.setTextDescription(productline2.getTextDescription());
        productlineRepository.update(productline);
        assertTrue(productlineRepository.findById(productline.getId()).getTextDescription().equals(productline2.getTextDescription()));
    }
}