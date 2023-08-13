package driver.database;

import businessrule.gateway.GenericGateway;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class GenericRepository<T> implements GenericGateway<T> {
    final Class<T> entityType;

    public GenericRepository(Class<T> entityType) {
        this.entityType = entityType;
    }

    @Override
    public void save(T entity) {
        executeTransaction(entityManager -> entityManager.persist(entity));
    }

    @Override
    public Object get(int id) {
        return entityType.cast(executeTransactionWithResult(entityManager -> entityManager.find(entityType, id)));
    }

    @Override
    public boolean existsById(int id) {
        return executeTransactionWithResult(entityManager -> {
            T entity = entityManager.find(entityType, id);
            return entity != null;
        });
    }

    @Override
    public void delete(int id) {
        executeTransaction(entityManager -> {
            T entity = entityManager.find(entityType, id);
            if (entity != null) {
                entityManager.remove(entity);
            }
        });
    }

    @Override
    public void deleteAll() {
        executeTransaction(entityManager -> entityManager.createQuery("DELETE FROM " + entityType.getSimpleName()).executeUpdate());
    }

    @Override
    public List<T> getAll() {
        return executeTransactionWithResult((EntityManager entityManager) -> entityManager.createQuery(
                "SELECT e FROM " + entityType.getSimpleName() + " e", entityType).getResultList());
    }

    void executeTransaction(DatabaseOperation operation) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            operation.accept(entityManager);
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

    <R> R executeTransactionWithResult(DatabaseOperationWithResult<R> operation) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return operation.apply(entityManager);
        } finally {
            entityManager.close();
        }
    }

    interface DatabaseOperation {
        void accept(EntityManager entityManager);
    }

    interface DatabaseOperationWithResult<R> {
        R apply(EntityManager entityManager);
    }

}
