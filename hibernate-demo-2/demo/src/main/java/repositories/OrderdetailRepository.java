package repositories;

import entities.Orderdetail;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class OrderdetailRepository {

    private EntityManager entityManager;

    public List<Orderdetail> findAll(){
        return entityManager.createNamedQuery("Orderdetail.findAll", Orderdetail.class).getResultList();
    }

    public List<Orderdetail> findById_OrderNumber(int orderNumber){
        return entityManager.createNamedQuery("Orderdetail.findById_OrderNumber", Orderdetail.class)
                .setParameter("orderNumber", orderNumber)
                .getResultList();
    }

    public void save(Orderdetail orderdetail){
        entityManager.getTransaction().begin();
        entityManager.persist(orderdetail);
        entityManager.getTransaction().commit();
    }

    public void remove(Orderdetail orderdetail){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(orderdetail) ? orderdetail : entityManager.merge(orderdetail));
        entityManager.getTransaction().commit();
    }

    public void update(Orderdetail orderdetail){
        entityManager.getTransaction().begin();
        Orderdetail od = entityManager.find(Orderdetail.class, orderdetail.getId());
        od.setQuantityOrdered(orderdetail.getQuantityOrdered());
        od.setPriceEach(orderdetail.getPriceEach());
        od.setOrderLineNumber(orderdetail.getOrderLineNumber());
        entityManager.getTransaction().commit();
    }
}
