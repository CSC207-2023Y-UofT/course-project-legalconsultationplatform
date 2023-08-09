package businessrule.gateway;

import entity.Client;

/**
 * This interface provides a method for retrieving a client by their ID.
 */
public interface ClientGateway extends UserGateway<Client> {

    /**
     * Get a client by their ID.
     *
     * @param id The ID of the client to retrieve.
     * @return The client object associated with the provided ID.
     */
    @Override
    Client get(int id);

}