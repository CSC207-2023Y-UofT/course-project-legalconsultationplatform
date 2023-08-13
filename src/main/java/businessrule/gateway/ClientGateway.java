package businessrule.gateway;

import entity.Client;

public interface ClientGateway extends UserGateway<Client> {

    @Override
    Client get(int id);
}
