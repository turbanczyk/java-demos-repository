package repositories;

import entities.Customer;
import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.*;

public class CustomerRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private CustomerRepository customerRepository;

    public CustomerRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        customerRepository = new CustomerRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Customer> customerList = customerRepository.findAll();
        assertTrue(customerList.size() == 122);
    }

    @Test
    public void findByCustomerName() {
        String customerName = "Marseille Mini Autos";
        List<Customer> customerList = customerRepository.findByCustomerName(customerName);
        assertTrue(customerList.size() == 1);
        assertTrue(customerList.get(0).getContactLastName().equals("Lebihan"));

        customerList = customerRepository.findByCustomerName("ABC");
        assertTrue(customerList.size() == 0);
    }

    @Test
    public void save() {
        Employee salesRepEmployeeNumber = new Employee();
        salesRepEmployeeNumber.setId(1002);

        Customer c = new Customer();
        c.setCustomerName("ABC models");
        c.setContactLastName("Smith");
        c.setContactFirstName("John");
        c.setPhone("123-456-789");
        c.setAddressLine1("John Smith Street");
        c.setAddressLine2(null);
        c.setCity("London");
        c.setState(null);
        c.setPostalCode("1234");
        c.setCountry("UK");
        c.setSalesRepEmployeeNumber(salesRepEmployeeNumber);
        c.setCreditLimit(new BigDecimal(55000));

        customerRepository.save(c);

        assertTrue(customerRepository.findAll().size() == 123);

        //clean
        Customer c2 = entityManager.find(Customer.class, c.getId());
        customerRepository.remove(c2);
        assertTrue(customerRepository.findAll().size() == 122);
    }

    @Test
    public void remove() {
        save();
    }

    @Test
    public void update() {
        int index = new Random().nextInt(122);
        Customer c = customerRepository.findAll().get(index);

        String city = c.getCity();
        String phone = c.getPhone();
        String customerName = c.getCustomerName();

        c.setCity("Unknown City");
        c.setPhone("000-000-000");
        customerRepository.update(c);

        assertTrue(customerRepository.findByCustomerName(customerName).get(0).getCity().equals("Unknown City"));
        assertTrue(customerRepository.findByCustomerName(customerName).get(0).getPhone().equals("000-000-000"));

        //clean
        c.setCity(city);
        c.setPhone(phone);
        customerRepository.update(c);
        assertTrue(customerRepository.findByCustomerName(customerName).get(0).getCity().equals(city));
        assertTrue(customerRepository.findByCustomerName(customerName).get(0).getPhone().equals(phone));
    }
}