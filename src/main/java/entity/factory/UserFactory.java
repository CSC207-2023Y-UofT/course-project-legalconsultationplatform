package entity.factory;

import businessrule.requestmodel.RegistrationData;
import entity.UserImp;

public interface UserFactory <T extends UserImp> {
    T createUser(RegistrationData data);
}
