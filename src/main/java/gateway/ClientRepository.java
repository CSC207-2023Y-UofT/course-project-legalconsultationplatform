package gateway;

import questionentities.Question;
import userentities.Client;
import userentities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.xml.crypto.Data;
import java.util.Date;

// TODO: implement this class
public class ClientRepository implements ClientGateway{

    @Override
    public boolean existsById(int userId) {
        return false;
    }

    @Override
    public boolean existsByName(String userName) {
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
    public void updateQuestionList(int userId, Question question) {

    }

    @Override
    public void addUser(User user) {
        EntityManagerFactory entityManagerFactory = DatabaseConnection.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
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
}

