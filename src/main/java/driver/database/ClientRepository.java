package driver.database;

import businessrule.gateway.ClientGateway;
import entity.Client;

public class ClientRepository extends UserRepository<Client> implements ClientGateway {

    public ClientRepository() {
        super(Client.class);
    }

}

