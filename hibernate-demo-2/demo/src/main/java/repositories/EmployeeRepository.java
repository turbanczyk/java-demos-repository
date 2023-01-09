package repositories;

import entities.Employee;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import lombok.AllArgsConstructor;

import java.util.List;

@AllArgsConstructor
public class EmployeeRepository {

    private EntityManager entityManager;

    public List<Employee> findAll(){
        /*
        TypedQuery query = entityManager.createNamedQuery("Employee.findAll", Employee.class);
        List<Employee> employeeList = query.getResultList();
        return employeeList;
         */
        return entityManager.createNamedQuery("Employee.findAll", Employee.class).getResultList();
    }

    public List<Employee> findByLastNameAndFirstName(String lastName, String firstName){
        TypedQuery query = entityManager.createNamedQuery("Employee.findByLastNameAndFirstName", Employee.class);
        query.setParameter("lastName", lastName);
        query.setParameter("firstName", firstName);
        List<Employee> employeeList = query.getResultList();
        return employeeList;
    }

    public void save(Employee employee){
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
    }

    public void remove(Employee employee){
        entityManager.getTransaction().begin();
        entityManager.remove(entityManager.contains(employee) ? employee : entityManager.merge(employee));
        entityManager.getTransaction().commit();
    }

    public void update(Employee employee){
        entityManager.getTransaction().begin();
        Employee e = entityManager.find(Employee.class, employee.getId());
        e.setLastName(employee.getLastName());
        e.setFirstName(employee.getFirstName());
        e.setExtension(employee.getExtension());
        e.setEmail(employee.getEmail());
        e.setOfficeCode(employee.getOfficeCode());
        e.setReportsTo(employee.getReportsTo());
        e.setJobTitle(employee.getJobTitle());
        entityManager.getTransaction().commit();
    }
}
