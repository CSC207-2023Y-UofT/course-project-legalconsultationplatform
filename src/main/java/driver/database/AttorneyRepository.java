package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.Question;
import entity.Attorney;
import entity.Client;
import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

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
        Attorney a = getUser(attorneyId);
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
        return false; //TODO
    }
}
