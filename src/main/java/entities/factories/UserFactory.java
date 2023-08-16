package entities.factories;

import entities.user.User;
import usecases.requests.RegistrationData;

/**
 * This is a interface represents creating user instances.
 *
 * @param <T> The type of user to create, extending UserImp.
 */
public interface UserFactory<T extends User> {

    /**
     * Creates a user instance based on the provided registration data.
     *
     * @param data The registration data for creating the user.
     * @return A new user instance.
     */
    T createUser(RegistrationData data);
}