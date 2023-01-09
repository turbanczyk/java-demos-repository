package repositories;

import entities.Customer;
import entities.Productline;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductlineRepository {

    private EntityManager entityManager;

    public List<Productline> findAll(){
        return entityManager.createNamedQuery("Productline.findAll", Productline.class).getResultList();
    }

    public Productline findById(String productLine){
        return entityManager.createNamedQuery("Productline.findById", Productline.class)
                .setParameter("id", productLine)
                .getSingleResult();
    }

    public void save(Productline productline){
        entityManager.getTransaction().begin();
        entityManager.persist(productline);
        entityManager.getTransaction().commit();
    }

    /*
    Remove limited only to productline not connected with any existing product.
     */
    public void remove(Productline productline){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(productline) ? productline : entityManager.merge(productline));
        entityManager.getTransaction().commit();
    }

    public void update(Productline productline){
        entityManager.getTransaction().begin();
        Productline p = entityManager.find(Productline.class, productline.getId());
        p.setTextDescription(productline.getTextDescription());
        p.setHtmlDescription(productline.getHtmlDescription());
        p.setImage(productline.getImage());
        entityManager.getTransaction().commit();
    }
}
