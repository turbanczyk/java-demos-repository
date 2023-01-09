
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // Create entity manager
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("classicmodels");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        /*

        No application code, refer to test section to check how repositories work, every repository has unit tests.

         */

        // Close the entity manager and associated factory
        entityManager.close();
        entityManagerFactory.close();
        
    }
}
