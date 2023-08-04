package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AttorneyRepository implements AttorneyGateway {

    @Override
    public boolean existsById(int attorneyId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            Attorney exists = entityManager.find(Attorney.class, attorneyId);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Attorney getUser(int attorneyId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return entityManager.find(Attorney.class, attorneyId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateQuestionList(int attorneyId, Question question) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Attorney a = em.find(Attorney.class, attorneyId);
        try {
            em.getTransaction().begin();
            a.addQuestion(question);
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
    public void addUser(User attorney) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(attorney);
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
    public void deleteUser(int attorneyId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Attorney a = em.find(Attorney.class, attorneyId);
            em.remove(a);
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
            em.createQuery("DELETE FROM Attorney a").executeUpdate();
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
    public boolean existsByName(String inputUserName) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            List<Attorney> users = em.createQuery("SELECT a FROM Attorney a WHERE a.name =: name", Attorney.class).
                    setParameter("name", inputUserName).getResultList();
            return !users.isEmpty();
        } finally {
            em.close();
        }
    }
}
