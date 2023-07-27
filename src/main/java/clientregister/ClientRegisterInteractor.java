package clientregister;
import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import userentities.Client;
import gateway.UserGateway;
import org.apache.commons.validator.routines.EmailValidator;


class ClientRegisterInteractor implements ClientRegisterInputBoundary{
    final UserGateway userGateway;
    final MessageOutputBoundary outputBoundary;
    final ClientFactory clientFactory;

    public ClientRegisterInteractor(UserGateway userGateway,
                                    MessageOutputBoundary outputBoundary,
                                    ClientFactory clientFactory) {
        this.userGateway = userGateway;
        this.outputBoundary = outputBoundary;
        this.clientFactory = clientFactory;
    }
    @Override
    public MessageResponseModel create(ClientRegisterRequestModel requestModel){
        String inputUserName = requestModel.getUserName();
        String inputPassword1 = requestModel.getPassword();
        String inputPassword2 = requestModel.getPassword2();
        String inputStateAbb = requestModel.getStateAbb();
        String inputPostalCode = requestModel.getPostalCode();
        String inputEthnicity = requestModel.getEthnicity();
        int inputAge = requestModel.getAge();
        String inputGender = requestModel.getGender();
        String inputMaritalStatus = requestModel.getMaritalStatus();
        int inputNumberOfHousehold = requestModel.getNumberOfHousehold();
        float inputAnnualIncome = requestModel.getAnnualIncome();

        if (userGateway.existsByName(inputUserName)){
            return outputBoundary.prepareFail("User name already exists");
        } else if (!inputPassword1.equals(inputPassword2)) {
            return outputBoundary.prepareFail("Passwords does not match");
        } else if (inputPassword1.length() <= 8){
            return outputBoundary.prepareFail("Password is invalid");
        }
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomUserId = generator.generateClientId(8);
        boolean exists = userGateway.existsById(randomUserId);
        while (exists){
            randomUserId = generator.generateClientId(8);
            exists = userGateway.existsById(randomUserId);
        }
        Client client = clientFactory.create(randomUserId, inputUserName, inputPassword1, inputStateAbb, inputPostalCode, inputEthnicity,
                inputAge, inputGender, inputMaritalStatus, inputNumberOfHousehold, inputAnnualIncome);
        userGateway.addUser(client);
        return outputBoundary.prepareSuccess("Client successfully registered");
    }
}
