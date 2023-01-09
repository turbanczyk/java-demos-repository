package repositories;

import entities.Office;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;

import java.util.List;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class OfficeRepository {

    private EntityManager entityManager;

    public List<Office> findAll(){
        /*
        TypedQuery query = entityManager.createNamedQuery("Office.findAll", Office.class);
        List<Office> officeList = query.getResultList();
        return officeList;
         */
        return entityManager.createNamedQuery("Office.findAll", Office.class).getResultList();
    }

    public List<Office> findByCity(String cityName){
        TypedQuery query = entityManager.createNamedQuery("Office.findByCity", Office.class);
        query.setParameter("city", cityName);
        List<Office> officeList = query.getResultList();
        return officeList;
    }

    public void save(Office office){
        entityManager.getTransaction().begin();
        entityManager.persist(office);
        entityManager.getTransaction().commit();
    }

    public void remove(Office office){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(office) ? office : entityManager.merge(office));
        entityManager.getTransaction().commit();
    }

    public void update(Office office){
        entityManager.getTransaction().begin();
        Office o = entityManager.find(Office.class, office.getId());
        o.setCity(office.getCity());
        o.setPhone(office.getPhone());
        o.setAddressLine1(office.getAddressLine1());
        o.setAddressLine2(office.getAddressLine2());
        o.setState(office.getState());
        o.setCountry(office.getCountry());
        o.setPostalCode(office.getPostalCode());
        o.setTerritory(office.getTerritory());
        entityManager.getTransaction().commit();
    }
}
