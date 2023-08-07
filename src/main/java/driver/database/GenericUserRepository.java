package driver.database;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class GenericUserRepository<T> implements UserRepository<T> {
    private Class<T> clazz;

    public GenericUserRepository(Class<T> clazz) {
        this.clazz = clazz;
    }

    @Override
    public boolean existsById(int id) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            T exists = entityManager.find(clazz, id);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public void addUser(User entity) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(entity);
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




