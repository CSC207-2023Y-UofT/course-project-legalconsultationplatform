package apapter.controller;

import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.RegisterResponseModel;

public class ClientRegisterControl {
    final ClientRegisterInputBoundary inputBoundary;

    public ClientRegisterControl(ClientRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

     RegisterResponseModel create(int userId, String email, String userName, String password1, String password2,
                                  String StateAbb, String PostalCode,
                                  String ethnicity, int age, String gender, String maritalStatus,
                                  int numberOfHousehold, float annualIncome){
        ClientRegisterRequestModel requestModel = new ClientRegisterRequestModel(userName, email, password1, password2, StateAbb,
                PostalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
        return inputBoundary.create(requestModel);
    }
}
