package entity.factory;

import businessrule.requestmodel.RegistrationData;
import entity.UserImp;

/**
 * This is a interface represents creating user instances.
 *
 * @param <T> The type of user to create, extending UserImp.
 */
public interface UserFactory<T extends UserImp> {

    /**
     * Creates a user instance based on the provided registration data.
     *
     * @param data The registration data for creating the user.
     * @return A new user instance.
     */
    T createUser(RegistrationData data);
}