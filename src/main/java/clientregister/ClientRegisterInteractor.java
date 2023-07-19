package clientregister;
import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import userentities.Client;
import usergateway.UserGateway;


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
        if (userGateway.existsById(requestModel.getUserId())){
            return outputBoundary.prepareFail("UserId already exists");
        } else if (!requestModel.getPassword().equals(requestModel.getPassword2())) {
            return outputBoundary.prepareFail("Passwords does not match");
        } else if (requestModel.getPassword().length() <= 8){
            return outputBoundary.prepareFail("Password is invalid");
        }
        Client client = clientFactory.create(requestModel.getUserId(), requestModel.getPassword(),
                requestModel.getStateAbb(), requestModel.getPostalCode(), requestModel.getEthnicity(),
                requestModel.getAge(), requestModel.getGender(), requestModel.getMaritalStatus(),
                requestModel.getNumberOfHousehold(), requestModel.getAnnualIncome());
        userGateway.addUser(client);
        return outputBoundary.prepareSuccess("Client successfully registered");
    }
}
