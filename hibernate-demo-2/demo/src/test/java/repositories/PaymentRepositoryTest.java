package repositories;

import entities.Customer;
import entities.Payment;
import entities.PaymentId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private PaymentRepository paymentRepository;
    private CustomerRepository customerRepository;

    public PaymentRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        paymentRepository = new PaymentRepository(entityManager);
        customerRepository = new CustomerRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Payment> paymentList = paymentRepository.findAll();
        assertTrue(paymentList.size() == 273);
        assertTrue(paymentList.get(272).getPaymentDate().equals(LocalDate.of(2004, 12, 31)));
        assertTrue(paymentList.get(272).getCustomerNumber().getId() == 496);
    }

    @Test
    public void findById_CustomerNumber() {
        List<Payment> paymentList = paymentRepository.findById_CustomerNumber(496);
        assertTrue(paymentList.size() == 3);

        paymentList = paymentRepository.findById_CustomerNumber(114);
        assertTrue(paymentList.size() == 4);
    }

    @Test
    public void save() {
        Payment payment = new Payment();
        Customer c = customerRepository.findByCustomerName("Kelly's Gift Shop").get(0);
        PaymentId paymentId = new PaymentId(c.getId(), "ABC123");
        payment.setId(paymentId);
        payment.setPaymentDate(LocalDate.of(2023, 1, 7));
        payment.setAmount(new BigDecimal(9333));
        payment.setCustomerNumber(c);

        paymentRepository.save(payment);
        assertTrue(paymentRepository.findAll().size() == 274);

        //clean
        paymentRepository.remove(payment);
        assertTrue(paymentRepository.findAll().size() == 273);
    }

    @Test
    public void remove() {
        Payment p = paymentRepository.findById_CustomerNumber(103).get(0);
        Payment p2 = p;

        paymentRepository.remove(p);
        assertTrue(paymentRepository.findAll().size() == 272);

        //clean
        paymentRepository.save(p2);
        assertTrue(paymentRepository.findAll().size() == 273);
    }

    @Test
    public void update() {
        Payment payment = paymentRepository.findAll().get(0);
        LocalDate paymentDate = payment.getPaymentDate();
        BigDecimal amount = payment.getAmount();

        payment.setPaymentDate(LocalDate.of(2023, 1, 8));
        payment.setAmount(new BigDecimal(1));
        paymentRepository.update(payment);

        assertTrue(paymentRepository.findAll().get(0).getPaymentDate().equals(LocalDate.of(2023, 1, 8)));
        assertTrue(paymentRepository.findAll().get(0).getAmount().equals(new BigDecimal(1)));

        //clean
        payment.setPaymentDate(paymentDate);
        payment.setAmount(amount);
        paymentRepository.update(payment);
        assertTrue(paymentRepository.findAll().get(0).getPaymentDate().equals(paymentDate));
        assertTrue(paymentRepository.findAll().get(0).getAmount().equals(amount));
    }
}