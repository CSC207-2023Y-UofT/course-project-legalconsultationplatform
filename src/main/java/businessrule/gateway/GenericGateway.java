package businessrule.gateway;

import java.util.List;

/**
 * This interface provides a generic gateway for accessing data and performing common operations.
 *
 * @param <T> The type of entity that this gateway will work with.
 */
public interface GenericGateway<T> {

    /**
     * Save an entity to the data source.
     *
     * @param entity The entity to be saved.
     */
    void save(T entity);

    /**
     * Get an entity by its ID.
     *
     * @param id The ID of the entity to retrieve.
     * @return The entity associated with the provided ID.
     */
    Object get(int id);

    /**
     * Check if an entity with a given ID exists.
     *
     * @param id The ID of the entity to check.
     * @return True if an entity with the provided ID exists, false otherwise.
     */
    boolean existsById(int id);

    /**
     * Delete an entity by its ID.
     *
     * @param id The ID of the entity to delete.
     */
    void delete(int id);

    /**
     * Delete all entities.
     */
    void deleteAll();

    /**
     * Get a list of all entities.
     *
     * @return A list containing all entities.
     */
    List<T> getAll();
}