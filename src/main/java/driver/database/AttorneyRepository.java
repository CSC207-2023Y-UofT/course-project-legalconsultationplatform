package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.*;

public class AttorneyRepository extends UserRepository<Attorney> implements AttorneyGateway {

    public AttorneyRepository() {
        super(Attorney.class);
    }

    @Override
    public Attorney get(int id) {
        return (Attorney) super.get(id);
    }
}
