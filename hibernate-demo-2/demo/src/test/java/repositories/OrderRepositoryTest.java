package repositories;

import entities.Order;
import entities.Customer;
import entities.Orderdetail;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Persistence;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class OrderRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private OrderRepository orderRepository;
    private CustomerRepository customerRepository;
    private OrderdetailRepository orderdetailRepository;

    public OrderRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        orderRepository = new OrderRepository(entityManager);
        customerRepository = new CustomerRepository(entityManager);
        orderdetailRepository = new OrderdetailRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Order> orderList = orderRepository.findAll();
        assertTrue(orderList.size() == 326);
        assertTrue(orderList.get(0).getStatus().equals("Shipped"));
        assertTrue(orderList.get(0).getShippedDate().equals(LocalDate.of(2003, 1, 10)));
    }

    @Test
    public void findById() {
        int index = new Random().nextInt(326);

        Order o = orderRepository.findAll().get(index);
        Order o2 = orderRepository.findById(o.getId());
        assertTrue(o2 != null);
        assertTrue(o2.getOrderDate().equals(o.getOrderDate()));
        assertTrue(o2.getShippedDate().equals(o.getShippedDate()));
    }

    @Test
    public void save() {
        Order o = new Order();
        Customer c = customerRepository.findByCustomerName("Atelier graphique").get(0);
        o.setOrderDate(LocalDate.of(2023, 1, 8));
        o.setRequiredDate(LocalDate.of(2023, 1, 24));
        o.setShippedDate(LocalDate.of(2023, 1, 15));
        o.setStatus("In preparation");
        o.setComments("No comments");
        o.setCustomerNumber(c);

        orderRepository.save(o);
        assertTrue(orderRepository.findAll().size() == 327);

        //clean
        orderRepository.remove(o);
        assertTrue(orderRepository.findAll().size() == 326);
    }

    @Test
    public void remove() {
        //Order o = orderRepository.findById(10101);
        int index = new Random().nextInt(122);

        Order o = orderRepository.findAll().get(index);
        Order o2 = o;

        List<Orderdetail> orderdetailList = orderRepository.remove(o);
        try{
            orderRepository.findById(o2.getId());
            fail();
        } catch (NoResultException e){
            assertTrue(true);
        }

        //clean
        o2.setId(null);
        orderRepository.save(o2);
        assertTrue(orderRepository.findAll().size() == 326);
        for(Orderdetail i : orderdetailList){
            orderdetailRepository.save(i);
        }
        assertTrue(orderdetailRepository.findAll().size() == 2996);
    }

    @Test
    public void update() {
        //Order o = orderRepository.findById(10101);
        Order o = orderRepository.findAll().get(new Random().nextInt(122));

        //orginal data
        LocalDate requiredDate = o.getRequiredDate();
        String comments = o.getComments();

        o.setRequiredDate(LocalDate.of(2023, 1, 8));
        o.setComments("New comments");
        orderRepository.update(o);

        assertTrue(orderRepository.findById(o.getId()).getRequiredDate().equals(LocalDate.of(2023, 1, 8)));
        assertTrue(orderRepository.findById(o.getId()).getComments().equals("New comments"));

        //clean
        o.setRequiredDate(requiredDate);
        o.setComments(comments);
        orderRepository.update(o);
        assertTrue(orderRepository.findById(o.getId()).getRequiredDate().equals(requiredDate));
        if(comments == null){
            assertTrue(orderRepository.findById(o.getId()).getComments() == null);
        }else {
            assertTrue(orderRepository.findById(o.getId()).getComments().equals(comments));
        }

    }
}