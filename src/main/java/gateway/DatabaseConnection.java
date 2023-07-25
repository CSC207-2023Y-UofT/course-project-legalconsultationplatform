package gateway;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;

public class DatabaseConnection {
    private static final String REMOTE_SERVER_ADDRESS = "192.168.2.28";
    private static final int SERVER_PORT = 6136;
    private static final String DATABASE_FOLDER = "CSC207Project";
    private static final String DATABASE_FILE = "legal_consultation_database.odb";
    private static final String URL =
            "objectdb://" + REMOTE_SERVER_ADDRESS + ":" + SERVER_PORT + "/" + DATABASE_FOLDER + "/" + DATABASE_FILE;
    private EntityManagerFactory entityManagerFactory;

    public EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            entityManagerFactory = Persistence.createEntityManagerFactory(URL);
        }
        return entityManagerFactory.createEntityManager();
    }

    public void closeEntityManager() {
        if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
            entityManagerFactory.close();
        }
    }
}

