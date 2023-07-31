package gateway;

import questionentities.Question;
import userentities.Client;
import userentities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

abstract class UserRepository implements UserGateway{

    @Override
    public boolean existsById(int userId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            User exists = entityManager.find(User.class, userId);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean isClient(int userId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            User user = entityManager.find(User.class, userId);
            return (user instanceof Client);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public User getUser(int userId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return entityManager.find(User.class, userId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void updateQuestionList(int userId, Question question) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        User user = getUser(userId);
        try {
            transaction.begin();
            user.addQuestion(question);
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

    @Override
    public void addUser(User user) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
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
