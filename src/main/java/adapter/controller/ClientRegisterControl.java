package adapter.controller;

import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.RegisterResponseModel;

public class ClientRegisterControl {
    private final UserRegisterInputBoundary inputBoundary;

    public ClientRegisterControl(UserRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

     public RegisterResponseModel create(String userName, String email, String password1, String password2,
                                         String StateAbb, String PostalCode,
                                         String ethnicity, int age, String gender, String maritalStatus,
                                         int numberOfHousehold, float annualIncome){
        RegistrationData requestModel = new RegistrationData(userName, email, password1, password2, StateAbb,
                PostalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
        return inputBoundary.create(requestModel);
    }
}
