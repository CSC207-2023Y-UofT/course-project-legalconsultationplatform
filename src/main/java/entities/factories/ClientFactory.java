package entities.factories;

import usecases.requests.RegistrationData;
import entities.user.Client;

/**
 * This class represents creating Client instances.
 */
public class ClientFactory implements UserFactory<Client> {

    /**
     * Creates a Client instance based on the provided registration data.
     *
     * @param data The registration data for creating the Client.
     * @return A new Client instance.
     */
    @Override
    public Client createUser(RegistrationData data) {
        return new Client.Builder(data).build();
    }
}