package businessrule.usecase;


import businessrule.gateway.ClientGateway;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.responsemodel.BaseResponseModel;
import entity.ApplicationException;
import entity.Client;
import entity.factory.ClientFactory;
import businessrule.usecase.util.CredentialChecker;
import businessrule.requestmodel.RegistrationData;

public class ClientRegisterInteractor extends UserRegisterInteractor<ClientGateway, ClientFactory, Client> {

    public ClientRegisterInteractor(ClientGateway userGateway, ClientFactory userFactory, BaseOutputBoundary outputBoundary) {
        super(userGateway, userFactory, outputBoundary);
    }

    @Override
    public BaseResponseModel create(RegistrationData requestModel) {
        try {checkCredential(requestModel);}
        catch (ApplicationException e) {
            return outputBoundary.prepareFail(e.getMessage());
        }
        int userId = generateId();
        String message = "Your userId is " + userId;
        return outputBoundary.prepareSuccess(message);
    }

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