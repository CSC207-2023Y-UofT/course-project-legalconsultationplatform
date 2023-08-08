package businessrule.gateway;

import entity.Client;
import entity.User;

public interface ClientGateway extends UserGateway<Client> {

    @Override
    Client get(int id);

}
