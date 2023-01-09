package repositories;

import static org.junit.Assert.*;

import entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import org.junit.After;
import org.junit.Test;

import java.util.List;

public class OfficeRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private OfficeRepository officeRepository;

    public OfficeRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        officeRepository = new OfficeRepository(entityManager);
    }

    @org.junit.Test
    public void findAll() {
        List<Office> officeList = officeRepository.findAll();
        assertTrue(officeList.size() == 7);
        assertTrue(officeList.get(0).getCity().equals("San Francisco"));
        assertTrue(officeList.get(6).getCountry().equals("UK"));
    }

    @Test
    public void findByCity() {
        List<Office> officeList = officeRepository.findByCity("London");
        assertTrue(officeList.size() == 1);
        assertTrue(officeList.get(0).getPhone().equals("+44 20 7877 2041"));

        officeList = officeRepository.findByCity("Warsaw");
        assertTrue(officeList.size() == 0);
    }

    @Test
    public void save() {
        Office o = new Office("8", "Berlin", "+49 111111111", "Strasse xyz 123",
                null, null, "Germany", "123456", "EMEA");
        officeRepository.save(o);
        assertTrue(officeRepository.findAll().size() == 8);

        //clean after save
        officeRepository.remove(o);
        assertTrue(officeRepository.findAll().size() == 7);
    }

    @Test
    public void remove() {
        Office o = new Office("8", "Berlin", "+49 111111111", "Strasse xyz 123",
                null, null, "Germany", "123456", "EMEA");

        officeRepository.save(o);
        assertTrue(officeRepository.findAll().size() == 8);

        officeRepository.remove(o);
        assertTrue(officeRepository.findAll().size() == 7);
    }

    @Test
    public void update() {
        Office o = new Office("1", "San Francisco", "+1 650 219 4782", "100 Market Street",
                "Suite 300", "CA", "USA", "94080", "NA");

        Office newO = new Office("1", "Berlin", "+1 650 219 4782", "100 Market Street",
                "Suite 300", "CA", "USA", "123456", "EMEA");

        officeRepository.update(newO);

        for (Office i : officeRepository.findAll()){
            if(i.getId().equals("1")){
                assertTrue(i.getCity().equals("Berlin"));
                assertTrue(i.getPostalCode().equals("123456"));
                assertTrue(i.getTerritory().equals("EMEA"));
            }
        }

        //clean after update
        officeRepository.update(o);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

}