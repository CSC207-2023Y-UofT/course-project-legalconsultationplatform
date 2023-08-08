package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.*;

import javax.jdo.JDOHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class AttorneyRepository extends UserRepository<Attorney> implements AttorneyGateway {

    public AttorneyRepository() {
        super(Attorney.class);
    }

}
