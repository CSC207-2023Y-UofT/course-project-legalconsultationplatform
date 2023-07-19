package clientregister;

import presenter.MessageResponseModel;

public class ClientRegisterControl {
    final ClientRegisterInputBoundary inputBoundary;

    public ClientRegisterControl(ClientRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    MessageResponseModel create(int userId, String password1, String password2,
                                String StateAbb, String PostalCode,
                                String ethnicity, int age, String gender, String maritalStatus,
                                int numberOfHousehold, float annualIncome){
        ClientRegisterRequestModel requestModel = new ClientRegisterRequestModel(userId, password1, password2, StateAbb,
                PostalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
        return inputBoundary.create(requestModel);
    }
}
