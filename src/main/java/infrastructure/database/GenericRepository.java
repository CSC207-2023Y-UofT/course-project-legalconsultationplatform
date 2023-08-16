package infrastructure.database;

import usecases.gateway.GenericGateway;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

/**
 * This class represents managing entities in the database.
 *
 * @param <T> The type of entity managed by the repository.
 */
public class GenericRepository<T> implements GenericGateway<T> {
    final Class<T> entityType;

    /**
     * Constructs a GenericRepository instance.
     *
     * @param entityType The class representing the type of entity managed by the repository.
     */
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
        executeTransaction(entityManager -> entityManager.createQuery("DELETE FROM " + entityType.getName()).executeUpdate());
    }

    @Override
    public List<T> getAll() {
        return executeTransactionWithResult((EntityManager entityManager) -> entityManager.createQuery(
                "SELECT e FROM " + entityType.getName() + " e", entityType).getResultList());
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

    /**
     * Represents an operation that accepts an EntityManager and performs an action.
     */
    interface DatabaseOperation {
        void accept(EntityManager entityManager);
    }

    /**
     * Represents an operation that accepts an EntityManager and returns a result.
     *
     * @param <R> The type of the result returned by the operation.
     */
    interface DatabaseOperationWithResult<R> {
        R apply(EntityManager entityManager);
    }
}