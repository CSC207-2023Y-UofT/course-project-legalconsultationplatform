package usecases.interactors;

import usecases.gateway.UserGateway;
import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.responses.BaseResponseModel;
import usecases.utils.RandomNumberGenerator;
import entities.user.User;
import usecases.requests.RegistrationData;
import entities.factories.UserFactory;

/**
 * This class represents the user registration interactors.
 *
 * @param <T> The type of UserGateway used for interacting with users.
 * @param <F> The type of UserFactory used for creating user instances.
 * @param <U> The type of User entity.
 */
public abstract class UserRegisterInteractor<T extends UserGateway<U>, F extends UserFactory<?>, U extends User> implements UserRegisterInputBoundary {
    protected final T userGateway;
    protected final F userFactory;
    protected final BaseOutputBoundary outputBoundary;

    /**
     * Constructor for UserRegisterInteractor.
     *
     * @param userGateway The gateway for user-related operations.
     * @param userFactory The factory for creating user instances.
     * @param outputBoundary The output boundary for registration results.
     */
    public UserRegisterInteractor(T userGateway, F userFactory, BaseOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.userFactory = userFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public abstract BaseResponseModel create(RegistrationData requestModel);

    /**
     * Generates a unique user ID.
     *
     * @return A unique user ID.
     */
    protected int generateId() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateClientId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists) {
            randomUserId = generator.generateClientId(8);
            exists = userGateway.existsById(randomUserId);
        } return randomUserId;
    }
}
