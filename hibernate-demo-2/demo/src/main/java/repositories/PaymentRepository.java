package repositories;

import entities.Payment;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class PaymentRepository {

    private EntityManager entityManager;

    public List<Payment> findAll(){
        return entityManager.createNamedQuery("Payment.findAll", Payment.class).getResultList();
    }

    public List<Payment> findById_CustomerNumber(int customerNumber){
        return entityManager.createNamedQuery("Payment.findById_CustomerNumber", Payment.class)
                .setParameter("customerNumber", customerNumber)
                .getResultList();
    }

    public void save(Payment payment){
        entityManager.getTransaction().begin();
        entityManager.persist(payment);
        entityManager.getTransaction().commit();
    }

    public void remove(Payment payment){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(payment) ? payment : entityManager.merge(payment));
        entityManager.getTransaction().commit();
    }

    public void update(Payment payment){
        entityManager.getTransaction().begin();
        Payment p = entityManager.find(Payment.class, payment.getId());
        p.setAmount(payment.getAmount());
        p.setPaymentDate(payment.getPaymentDate());
        entityManager.getTransaction().commit();
    }
}
