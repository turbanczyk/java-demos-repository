package repositories;

import entities.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class CustomerRepository {

    private EntityManager entityManager;

    public List<Customer> findAll(){
        return entityManager.createNamedQuery("Customer.findAll", Customer.class).getResultList();
    }

    public List<Customer> findByCustomerName(String customerName){
        return entityManager.createNamedQuery("Customer.findByCustomerName", Customer.class)
                .setParameter("customerName", customerName)
                .getResultList();
    }

    public void save(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.persist(customer);
        entityManager.getTransaction().commit();
    }

    /*
    Limitation, remove work only if there is no order where customer exist. There is no possible to remove customer
    if there is order connected with him.
     */
    public void remove(Customer customer){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(customer) ? customer : entityManager.merge(customer));
        entityManager.getTransaction().commit();
    }

    public void update(Customer customer){
        entityManager.getTransaction().begin();
        Customer c = entityManager.find(Customer.class, customer.getId());
        c.setCustomerName(customer.getCustomerName());
        c.setContactLastName(customer.getContactLastName());
        c.setContactFirstName(customer.getContactFirstName());
        c.setPhone(customer.getPhone());
        c.setAddressLine1(customer.getAddressLine1());
        c.setAddressLine2(customer.getAddressLine2());
        c.setCity(customer.getCity());
        c.setState(customer.getState());
        c.setPostalCode(customer.getPostalCode());
        c.setCountry(customer.getCountry());
        c.setSalesRepEmployeeNumber(customer.getSalesRepEmployeeNumber());
        c.setCreditLimit(customer.getCreditLimit());
        entityManager.getTransaction().commit();
    }
}
