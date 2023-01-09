package repositories;

import entities.Orderdetail;
import entities.OrderdetailId;
import entities.Product;
import entities.Order;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.util.List;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderdetailRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private PaymentRepository paymentRepository;
    private OrderdetailRepository orderdetailRepository;
    private OrderRepository orderRepository;
    private ProductRepository productRepository;

    public OrderdetailRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        orderdetailRepository = new OrderdetailRepository(entityManager);
        orderRepository = new OrderRepository(entityManager);
        productRepository = new ProductRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Orderdetail> orderdetailList = orderdetailRepository.findAll();
        assertTrue(orderdetailList.size() == 2996);
        assertTrue(orderdetailList.get(0).getQuantityOrdered() == 30);
    }

    @Test
    public void findById_OrderNumber() {
        List<Orderdetail> orderdetailList = orderdetailRepository.findById_OrderNumber(10101);
        assertTrue(orderdetailList.size() == 4);

        orderdetailList = orderdetailRepository.findById_OrderNumber(10103);
        assertTrue(orderdetailList.size() == 16);
    }

    @Test
    public void save() {
        Orderdetail orderdetail = new Orderdetail();

        Product p = productRepository.findAll().get(10);
        Order o = orderRepository.findAll().get(3);

        OrderdetailId orderdetailId = new OrderdetailId();
        orderdetailId.setProductCode(p.getId());
        orderdetailId.setOrderNumber(o.getId());
        orderdetail.setId(orderdetailId);

        orderdetail.setQuantityOrdered(30);
        orderdetail.setPriceEach(new BigDecimal(23.3));
        short s = 2;
        orderdetail.setOrderLineNumber(s);
        orderdetail.setOrderNumber(o);
        orderdetail.setProductCode(p);

        orderdetailRepository.save(orderdetail);
        assertTrue(orderdetailRepository.findAll().size() == 2997);

        //clean
        orderdetailRepository.remove(orderdetail);
        assertTrue(orderdetailRepository.findAll().size() == 2996);
    }

    @Test
    public void remove() {
        Order order = orderRepository.findAll().get(0);
        List<Orderdetail> orderdetailList = orderdetailRepository.findById_OrderNumber(order.getId());
        Orderdetail orderdetail = orderdetailList.get(0);
        Orderdetail orderdetail2 = orderdetail;

        orderdetailRepository.remove(orderdetail);
        assertTrue(orderdetailRepository.findById_OrderNumber(order.getId()).size() == (orderdetailList.size() - 1));
        assertTrue(orderdetailRepository.findAll().size() == 2995);

        //clean
        orderdetailRepository.save(orderdetail2);
        assertTrue(orderdetailRepository.findById_OrderNumber(order.getId()).size() == orderdetailList.size());
        assertTrue(orderdetailRepository.findAll().size() == 2996);
    }

    @Test
    public void update() {
        Order o = orderRepository.findAll().get(27);
        Orderdetail orderdetail = orderdetailRepository.findById_OrderNumber(o.getId()).get(0);
        int quantityOrdered = orderdetail.getQuantityOrdered();

        orderdetail.setQuantityOrdered(120);
        orderdetailRepository.update(orderdetail);
        assertTrue(orderdetailRepository.findById_OrderNumber(o.getId()).get(0).getQuantityOrdered() == 120);

        //clean
        orderdetail.setQuantityOrdered(quantityOrdered);
        orderdetailRepository.update(orderdetail);
        assertTrue(orderdetailRepository.findById_OrderNumber(o.getId()).get(0).getQuantityOrdered() == quantityOrdered);
    }
}