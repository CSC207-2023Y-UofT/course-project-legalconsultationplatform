package gateway;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DatabaseConnection {

    private static final String URL = "objectdb:127.0.0.1:6136/db/";
    private String fileName;
    private EntityManagerFactory entityManagerFactory;

    public DatabaseConnection(String fileName) {
        this.fileName = fileName;
    }

    public EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(URL + fileName + ".odb");
        }
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

