package businessrule.usecase;

import businessrule.gateway.UserGateway;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.responsemodel.BaseResponseModel;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.User;
import businessrule.requestmodel.RegistrationData;
import entity.factory.UserFactory;

public abstract class UserRegisterInteractor<T extends UserGateway<U>, F extends UserFactory<?>, U extends User> implements UserRegisterInputBoundary {
    protected final T userGateway;
    protected final F userFactory;
    protected final BaseOutputBoundary outputBoundary;

    public UserRegisterInteractor(T userGateway, F userFactory, BaseOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.userFactory = userFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public abstract BaseResponseModel create(RegistrationData requestModel);

    protected int generateId() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateClientId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists) {
            randomUserId = generator.generateClientId(8);
            exists = userGateway.existsById(randomUserId);
        } return randomUserId;
    }

    protected int saveUser(U user) {
        userGateway.save(user);
        return user.getUserId();
    }
}
