package gateway;

import questionentities.Question;
import userentities.Client;
import userentities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

// TODO: implement this class
public class ClientRepository implements ClientGateway{
    final DatabaseConnection databaseConnection;

    public ClientRepository(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    @Override
    public boolean existsById(int userId) {
        return false;
    }

    @Override
    public boolean isClient(int userId) {
        return false;
    }

    @Override
    public User getUser(int userId) {
        return null;
    }

    @Override
    public void updateQuestionList(Question question) {

    }

    @Override
    public void addUser(User user) {
        EntityManager entityManager = databaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(user);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    public static void main(String[] args) {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        ClientRepository clientRepository = new ClientRepository(databaseConnection);

        Client client = new Client(10000000, "simonliu1122", "AB", "12345",
                "Chinese", 21, "Male", "No", 3, 15000);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("objectdb://localhost/db");
        emf.createEntityManager();
        emf.close();

    }
}

