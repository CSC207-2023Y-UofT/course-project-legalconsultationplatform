package businessrule.gateway;

import entity.Question;
import entity.User;

/**
 * This interface provides methods for checking if a user exists by name, updating a user's question list,
 * and retrieving a user by their ID.
 *
 * @param <T> The type of user entity.
 */
public interface UserGateway<T extends User> extends GenericGateway<T> {

    /**
     * Check if a user with the specified name exists.
     *
     * @param inputUserName The name of the user to check.
     * @return True if a user with the provided name exists, false otherwise.
     */
    boolean existsByName(String inputUserName);

    /**
     * Update the question list of a user.
     *
     * @param id The ID of the user to update.
     * @param question The question to add to the user's question list.
     */
    void updateQuestionList(int id, Question question);

    /**
     * Get a user by their ID.
     *
     * @param id The ID of the user to retrieve.
     * @return The user object associated with the provided ID.
     */
    @Override
    User get(int id);
}