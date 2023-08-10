package businessrule.usecase;

import businessrule.gateway.UserGateway;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.User;
import businessrule.requestmodel.RegistrationData;
import entity.factory.UserFactory;

public abstract class UserRegisterInteractor<T extends UserGateway<U>, F extends UserFactory<?>, U extends User> implements UserRegisterInputBoundary {
    protected final T userGateway;
    protected final F userFactory;
    protected final RegisterOutputBoundary outputBoundary;

    public UserRegisterInteractor(T userGateway, F userFactory, RegisterOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.userFactory = userFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public abstract RegisterResponseModel create(RegistrationData requestModel);

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
