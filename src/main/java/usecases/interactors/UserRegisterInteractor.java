package usecases.interactors;

import entities.ApplicationException;
import usecases.gateway.UserGateway;
import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.responses.BaseResponseModel;
import usecases.utils.CredentialChecker;
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
public abstract class UserRegisterInteractor<T extends UserGateway<U>, F extends UserFactory<U>, U extends User> implements UserRegisterInputBoundary {
    protected final T userGateway;
    protected final F userFactory;

    protected final BaseOutputBoundary outputBoundary;
    protected static final CredentialChecker checker = new CredentialChecker();

    /**
     * Constructor for UserRegisterInteractor.
     *
     * @param userGateway    The gateway for user-related operations.
     * @param outputBoundary The output boundary for registration results.
     * @param userFactory The user factory to create corresponding user entity.
     */
    public UserRegisterInteractor(T userGateway, BaseOutputBoundary outputBoundary, F userFactory) {
        this.userGateway = userGateway;
        this.outputBoundary = outputBoundary;
        this.userFactory = userFactory;
    }

    public BaseResponseModel create(RegistrationData requestModel) {
        try {checkCredential(requestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }
        int userId = generateId();
        requestModel.setUserId(userId);
        U user = userFactory.createUser(requestModel);
        userGateway.save(user);
        return outputBoundary.prepareSuccess(String.valueOf(userId));
    }

    protected int generateId() {
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateClientId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists) {
            randomUserId = generator.generateClientId(8);
            exists = userGateway.existsById(randomUserId);
        } return randomUserId;
    }

    protected void checkCredential(RegistrationData requestModel) throws ApplicationException {
        // prepare input data
        String inputUserName = requestModel.userName;
        String inputEmail = requestModel.email;
        String inputPassword1 = requestModel.password;
        String inputPassword2 = requestModel.password2;
        String inputPostalCode = requestModel.postalCode;
        String inputStateAbb = requestModel.stateAbb;

        // validate input data
        if (userGateway.existsByName(inputUserName)) {
            throw new ApplicationException("User name already exists");
        } else if (!inputPassword1.equals(inputPassword2)) {
            throw new ApplicationException("Passwords does not match");
        } else if (inputPassword1.length() < 8) {
            throw new ApplicationException("Password is less than 8 characters");
        } else if (!checker.checkEmail(inputEmail)) {
            throw new ApplicationException("Email is invalid");
        } else if (!checker.checkPostalCode(inputPostalCode)) {
            throw new ApplicationException("Postal Code is invalid");
        } else if (!checker.checkStateAbb(inputStateAbb)){
            throw new ApplicationException("State Abbreviation is invalid");
        }
    }
}
