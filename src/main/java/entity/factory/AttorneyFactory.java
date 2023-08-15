package entity.factory;

import businessrule.requestmodel.RegistrationData;
import entity.Attorney;

/**
 * This class represents creating Attorney instances.
 */
public class AttorneyFactory implements UserFactory<Attorney> {

    /**
     * Creates an Attorney instance based on the provided registration data.
     *
     * @param data The registration data for creating the Attorney.
     * @return A new Attorney instance.
     */
    @Override
    public Attorney createUser(RegistrationData data) {
        return new Attorney.Builder(data).build();
    }
}