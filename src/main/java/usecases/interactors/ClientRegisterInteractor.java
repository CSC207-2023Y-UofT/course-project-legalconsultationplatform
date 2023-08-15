package usecases.interactors;


import usecases.gateway.ClientGateway;
import usecases.outputboundary.BaseOutputBoundary;
import usecases.responses.BaseResponseModel;
import entities.ApplicationException;
import entities.user.Client;
import entities.factories.ClientFactory;
import usecases.utils.CredentialChecker;
import usecases.requests.RegistrationData;

/**
 * This class represents interactor responsible for client registration use case.
 */
public class ClientRegisterInteractor extends UserRegisterInteractor<ClientGateway, ClientFactory, Client> {

    /**
     * Constructs a ClientRegisterInteractor.
     *
     * @param userGateway   The gateway for user-related operations.
     * @param userFactory   The factory for creating user entities.
     * @param outputBoundary The output boundary for the registration process.
     */
    public ClientRegisterInteractor(ClientGateway userGateway, ClientFactory userFactory, BaseOutputBoundary outputBoundary) {
        super(userGateway, userFactory, outputBoundary);
    }

    /**
     * Create a new client registration based on the provided registration data.
     *
     * @param requestModel The registration data for the client.
     * @return The response model indicating the result of the registration process.
     */
    @Override
    public BaseResponseModel create(RegistrationData requestModel) {
        try {checkCredential(requestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }
        int userId = generateId();
        requestModel.setUserId(userId);
        Client client = userFactory.createUser(requestModel);
        userGateway.save(client);
        return outputBoundary.prepareSuccess(String.valueOf(userId));
    }

    /**
     * Check the credentials and validate the input data for client registration.
     *
     * @param requestModel The registration data for the client.
     * @throws ApplicationException If there are issues with the provided credentials or input data.
     */
    private void checkCredential(RegistrationData requestModel) throws ApplicationException{
        // prepare input data
        String inputUserName = requestModel.userName;
        String inputEmail = requestModel.email;
        String inputPassword1 = requestModel.password;
        String inputPassword2 = requestModel.password2;
        String inputPostalCode = requestModel.postalCode;
        int inputAge = requestModel.age;

        // validate input data
        CredentialChecker checker = new CredentialChecker();
        if (userGateway.existsByName(inputUserName)) {
            throw new ApplicationException("User name already exists");
        } else if (!inputPassword1.equals(inputPassword2)) {
            throw new ApplicationException("Passwords does not match");
        } else if (inputPassword1.length() < 8) {
            throw new ApplicationException("Password is less than 8 characters");
        } else if (!checker.checkEmail(inputEmail)) {
            throw new ApplicationException("Email is invalid");
        } else if (!checker.checkAge(inputAge)) {
            throw new ApplicationException("Age is invalid");
        } else if (!checker.checkPostalCode(inputPostalCode)) {
            throw new ApplicationException("Postal Code is invalid");
        }
    }
}