package repositories;

import entities.Product;
import entities.Productline;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private ProductRepository productRepository;

    public ProductRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        productRepository = new ProductRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Product> productList = productRepository.findAll();
        assertTrue(productList.size() == 110);
        assertTrue(productList.get(0).getId().equals("S10_1678"));
        assertTrue(productList.get(0).getProductName().equals("1969 Harley Davidson Ultimate Chopper"));
    }

    @Test
    public void findById() {
        Product product = productRepository.findById("S12_2823");
        assertTrue(product != null);
        assertTrue(product.getProductName().equals("2002 Suzuki XREO"));
        assertTrue(product.getProductScale().equals("1:12"));
    }

    @Test
    public void save() {
        Product p = new Product();
        p.setId("S08_1642");
        p.setProductName("Sample name of product");
        Productline productline = new Productline();
        productline.setId("Classic Cars");
        p.setProductLine(productline);
        p.setProductScale("1:08");
        p.setProductVendor("Sample vendor");
        p.setProductDescription("Sample description");
        short s = 24;
        p.setQuantityInStock(s);
        p.setBuyPrice(new BigDecimal(23.77));
        p.setMsrp(new BigDecimal(150.2));

        productRepository.save(p);
        assertTrue(productRepository.findAll().size() == 111);

        //clean
        productRepository.remove(p);
        assertTrue(productRepository.findAll().size() == 110);
    }

    @Test
    public void remove() {
        save();
    }

    @Test
    public void update() {
        Product product = productRepository.findById("S10_1678");
        String productName = product.getProductName();
        String productScale = product.getProductScale();

        product.setProductName("New product name");
        product.setProductScale("1:12");
        productRepository.update(product);

        assertTrue(productRepository.findById("S10_1678").getProductName().equals("New product name"));
        assertTrue(productRepository.findById("S10_1678").getProductScale().equals("1:12"));

        //clean
        product.setProductName(productName);
        product.setProductScale(productScale);
        productRepository.update(product);

        assertTrue(productRepository.findById("S10_1678").getProductName().equals(productName));
        assertTrue(productRepository.findById("S10_1678").getProductScale().equals(productScale));
    }
}