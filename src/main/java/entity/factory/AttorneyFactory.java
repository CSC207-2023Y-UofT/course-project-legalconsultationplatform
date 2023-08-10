package entity.factory;

import businessrule.requestmodel.RegistrationData;
import entity.Attorney;

public class AttorneyFactory implements UserFactory<Attorney> {
    @Override
    public Attorney createUser(RegistrationData data) {
        return new Attorney.Builder(data).build();
    }
}
