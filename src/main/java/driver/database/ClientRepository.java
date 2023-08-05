package driver.database;

import businessrule.gateway.ClientGateway;
import entity.Question;
import entity.Client;
import entity.User;

import javax.jdo.JDOHelper;
import javax.persistence.*;
import java.util.List;

public class ClientRepository implements ClientGateway {

    @Override
    public boolean existsById(int clientId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            Client exists = entityManager.find(Client.class, clientId);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Client getUser(int clientId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return entityManager.find(Client.class, clientId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addUser(User client) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(client);
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

    @Override
    public void deleteUser(int clientId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Client c = em.find(Client.class, clientId);
            em.remove(c);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAllUser() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Client c").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsByName(String username) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            List<Client> users = em.createQuery("SELECT c FROM Client c WHERE c.userName =: username", Client.class).
                    setParameter("username", username).getResultList();
            return !users.isEmpty();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Question> getAllQuestionById(int clientId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q WHERE q.askedByClient =: clientId", Question.class).
                    setParameter("clientId", clientId).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateQuestionList(int clientId, Question question) {
        EntityManager em = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Client c = em.find(Client.class, clientId);
        try {
            transaction.begin();
            c.addQuestion(question);
            JDOHelper.makeDirty(c, "questionsList");
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

}

