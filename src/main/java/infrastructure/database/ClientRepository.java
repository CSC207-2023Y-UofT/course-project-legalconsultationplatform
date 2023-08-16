package infrastructure.database;

import usecases.gateway.ClientGateway;
import entities.user.Client;

/**
 * This class represents managing Client entities in the database.
 */
public class ClientRepository extends UserRepository<Client> implements ClientGateway {

    /**
     * Constructs a ClientRepository instance.
     * Initializes the superclass with the Client class.
     */
    public ClientRepository() {
        super(Client.class);
    }

    /**
     * Retrieves a Client entity with the specified ID from the database.
     *
     * @param id The ID of the Client entity to retrieve.
     * @return The Client entity associated with the specified ID, or null if not found.
     */
    @Override
    public Client get(int id) {
        return (Client) super.get(id);
    }
}