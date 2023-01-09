package repositories;

import entities.Order;
import entities.Orderdetail;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderRepository {

    private EntityManager entityManager;

    public List<Order> findAll(){
        return entityManager.createNamedQuery("Order.findAll", Order.class).getResultList();
    }

    public Order findById(int orderId){
        return entityManager.createNamedQuery("Order.findById", Order.class)
                .setParameter("id", orderId)
                .getSingleResult();
    }

    public void save(Order order){
        entityManager.getTransaction().begin();
        entityManager.persist(order);
        entityManager.getTransaction().commit();
    }

    public List<Orderdetail> remove(Order order){
        //first remove order details
        OrderdetailRepository orderdetailRepository = new OrderdetailRepository(entityManager);
        List<Orderdetail> orderdetailList = orderdetailRepository.findById_OrderNumber(order.getId());
        for(Orderdetail o : orderdetailList){
            orderdetailRepository.remove(o);
        }

        //next remove order
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(order) ? order : entityManager.merge(order));
        entityManager.getTransaction().commit();

        //return list of orderdetail elements
        return orderdetailList;
    }

    public void update(Order order){
        entityManager.getTransaction().begin();
        Order o = entityManager.find(Order.class, order.getId());
        o.setOrderDate(order.getOrderDate());
        o.setRequiredDate(order.getRequiredDate());
        o.setShippedDate(order.getShippedDate());
        o.setStatus(order.getStatus());
        o.setComments(order.getComments());
        o.setCustomerNumber(order.getCustomerNumber());
        entityManager.getTransaction().commit();
    }
}
