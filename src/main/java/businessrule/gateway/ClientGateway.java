package businessrule.gateway;

import driver.database.UserGateway;

public interface ClientGateway extends UserGateway {

    boolean existsByUsername(String username);

}
