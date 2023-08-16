package infrastructure.database;

import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

/**
 * This class represents a utility for managing the Database Connection and Entity Manager.
 */
public class DatabaseConnection {

//    uncomment the line below to switch to the remote connection
//    the remote server connection has some JPA exception now, we are still working to fix it
    private static final String URL = "objectdb:myapp.odb;admin";
//    private static final String URL = "objectdb://175.24.206.37:39136/myapp.odb;admin";
    private static EntityManagerFactory entityManagerFactory;

    /**
     * Retrieves an instance of EntityManager for database interaction.
     *
     * @return An EntityManager instance.
     */
    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(URL);
        }
        return entityManagerFactory.createEntityManager();
    }

    /**
     * Closes the EntityManagerFactory if it is open.
     */
    public static void closeEntityManagerFactory() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}