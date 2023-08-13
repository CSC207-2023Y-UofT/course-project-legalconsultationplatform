package businessrule.usecase;

import businessrule.gateway.UserGateway;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import businessrule.usecase.util.RandomNumberGenerator;
import entity.User;
import businessrule.requestmodel.RegistrationData;
import entity.factory.UserFactory;

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
    protected final RegisterOutputBoundary outputBoundary;

    /**
     * Constructor for UserRegisterInteractor.
     *
     * @param userGateway The gateway for user-related operations.
     * @param userFactory The factory for creating user instances.
     * @param outputBoundary The output boundary for registration results.
     */
    public UserRegisterInteractor(T userGateway, F userFactory, RegisterOutputBoundary outputBoundary) {
        this.userGateway = userGateway;
        this.userFactory = userFactory;
        this.outputBoundary = outputBoundary;
    }

    @Override
    public abstract RegisterResponseModel create(RegistrationData requestModel);

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
        }
        return randomUserId;
    }

    /**
     * Saves a user entity and returns the user's ID.
     *
     * @param user The user entity to be saved.
     * @return The user's ID after saving.
     */
    protected int saveUser(U user) {
        userGateway.save(user);
        return user.getUserId();
    }
}