package repositories;

import entities.Product;
import jakarta.persistence.EntityManager;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class ProductRepository {

    private EntityManager entityManager;

    public List<Product> findAll(){
        return entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
    }

    public Product findById(String id){
        return entityManager.createNamedQuery("Product.findById", Product.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    public void save(Product product){
        entityManager.getTransaction().begin();
        entityManager.persist(product);
        entityManager.getTransaction().commit();
    }


    /*
    Product remove doesn't work with Product where exist orderdetail which consist this product,
    because cascade remove will remove also orderdetails (current
    and archive, problem is with archive because we will remove history). If product is not valid anymore
    the quantity in stock should be set on value 0 and product should stay as it is (shouldn't be removed).
     */
    public void remove(Product product){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(product) ? product : entityManager.merge(product));
        entityManager.getTransaction().commit();
    }


    public void update(Product product){
        entityManager.getTransaction().begin();
        Product p = entityManager.find(Product.class, product.getId());
        p.setProductName(product.getProductName());
        p.setProductLine(product.getProductLine());
        p.setProductScale(product.getProductScale());
        p.setProductVendor(product.getProductVendor());
        p.setProductDescription(product.getProductDescription());
        p.setQuantityInStock(product.getQuantityInStock());
        p.setBuyPrice(product.getBuyPrice());
        p.setMsrp(product.getMsrp());
        entityManager.getTransaction().commit();
    }
}
