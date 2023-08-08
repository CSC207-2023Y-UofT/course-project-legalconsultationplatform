package businessrule.gateway;

import entity.Attorney;
import entity.User;

public interface AttorneyGateway extends UserGateway<Attorney> {

    @Override
    Attorney get(int id);

}
