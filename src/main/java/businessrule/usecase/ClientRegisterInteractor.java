package businessrule.usecase;


import businessrule.gateway.ClientGateway;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import entity.ApplicationException;
import entity.Client;
import entity.factory.ClientFactory;
import businessrule.usecase.util.CredentialChecker;
import businessrule.requestmodel.RegistrationData;

/**
 * This class represents interactor responsible for client registration use case.
 */
public class ClientRegisterInteractor extends UserRegisterInteractor<ClientGateway, ClientFactory, Client> {

    /**
     * Constructs a ClientRegisterInteractor.
     *
     * @param clientGateway   The gateway for client-related operations.
     * @param clientFactory   The factory for creating client entities.
     * @param outputBoundary The output boundary for the registration process.
     */
    public ClientRegisterInteractor(ClientGateway clientGateway, ClientFactory clientFactory, RegisterOutputBoundary outputBoundary) {
        super(clientGateway, clientFactory, outputBoundary);
    }

    /**
     * Create a new client registration based on the provided registration data.
     *
     * @param requestModel The registration data for the client.
     * @return The response model indicating the result of the registration process.
     */
    @Override
    public RegisterResponseModel create(RegistrationData requestModel) {
        try {checkCredential(requestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }
        int userId = generateId();
        String message = "Your userId is " + userId;
        return outputBoundary.prepareSuccess(message);
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
        String inputStateAbb = requestModel.stateAbb;
        String inputPostalCode = requestModel.postalCode;
        String inputEthnicity = requestModel.ethnicity;
        int inputAge = requestModel.age;
        String inputGender = requestModel.gender;
        String inputMaritalStatus = requestModel.maritalStatus;
        int inputNumberOfHousehold = requestModel.numberOfHousehold;
        float inputAnnualIncome = requestModel.annualIncome;

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