package repositories;

import entities.Employee;
import entities.Office;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import java.util.List;
import java.util.Random;

import org.junit.After;
import org.junit.Test;

import static org.junit.Assert.*;

public class EmployeeRepositoryTest {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private EmployeeRepository employeeRepository;

    public EmployeeRepositoryTest(){
        entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels_test");
        entityManager = entityManagerFactory.createEntityManager();
        employeeRepository = new EmployeeRepository(entityManager);
    }

    @After
    public void tearDown() throws Exception {
        entityManager.close();
        entityManagerFactory.close();
    }

    @Test
    public void findAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        assertTrue(employeeList.size() == 23);
        assertTrue(employeeList.get(0).getLastName().equals("Murphy"));
        assertTrue(employeeList.get(11).getExtension().equals("x4102"));
    }

    @Test
    public void findByLastNameAndFirstName() {
        int index = new Random().nextInt(23);
        Employee employee = employeeRepository.findAll().get(index);

        String lastName = employee.getLastName();
        String firsName = employee.getFirstName();

        List<Employee> employeeList = employeeRepository.findByLastNameAndFirstName(lastName, firsName);
        assertTrue(employeeList.size() == 1);
        assertTrue(employeeList.get(0).getId() == employee.getId());

        employeeList = employeeRepository.findByLastNameAndFirstName("Smith", "John");
        assertTrue(employeeList.size() == 0);
    }

    @Test
    public void save() {
        Office o = new Office();
        o.setId("3");

        Employee e = new Employee();
        e.setLastName("Smith");
        e.setFirstName("John");
        e.setExtension("x105");
        e.setEmail("smith@classicmodelcars.com");
        e.setOfficeCode(o);
        e.setReportsTo(null);
        e.setJobTitle("Sales Rep");

        employeeRepository.save(e);

        assertTrue(employeeRepository.findAll().size() == 24);

        //clean
        Employee e2 = entityManager.find(Employee.class, e.getId());
        employeeRepository.remove(e2);
        assertTrue(employeeRepository.findAll().size() == 23);
    }

    @Test
    public void remove() {
        Office o = new Office();
        o.setId("3");

        Employee e = new Employee();
        e.setLastName("Smith");
        e.setFirstName("John");
        e.setExtension("x105");
        e.setEmail("smith@classicmodelcars.com");
        e.setOfficeCode(o);
        e.setReportsTo(null);
        e.setJobTitle("Sales Rep");

        employeeRepository.save(e);

        assertTrue(employeeRepository.findAll().size() == 24);

        //clean
        Employee e2 = entityManager.find(Employee.class, e.getId());
        employeeRepository.remove(e2);
        assertTrue(employeeRepository.findAll().size() == 23);
    }

    @Test
    public void update() {
        Employee e = employeeRepository.findByLastNameAndFirstName("Jones", "Barry").get(0);

        e.setFirstName("James");
        e.setJobTitle("Sales Manager (APAC)");
        employeeRepository.update(e);

        Employee updatedE = employeeRepository.findByLastNameAndFirstName("Jones", "James").get(0);
        assertTrue(updatedE.getFirstName().equals("James"));
        assertTrue(updatedE.getJobTitle().equals("Sales Manager (APAC)"));

        //clean
        e.setFirstName("Barry");
        e.setJobTitle("Sales Rep");
        employeeRepository.update(e);
    }
}