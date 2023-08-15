package adapters.controllers;

import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.requests.RegistrationData;
import usecases.responses.BaseResponseModel;

/**
 * This class represents responsible for handling user registration.
 */
public class ClientRegisterControl {
    private final UserRegisterInputBoundary inputBoundary;

    /**
     * Constructs a new instance of ClientRegisterControl.
     *
     * @param inputBoundary The input boundary for user registration.
     */
    public ClientRegisterControl(UserRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Creates a new user registration based on the provided information.
     *
     * @param userName The username of the user.
     * @param email The email address of the user.
     * @param password1 The first password.
     * @param password2 The confirmation password.
     * @param StateAbb The state abbreviation.
     * @param PostalCode The postal code.
     * @param ethnicity The ethnicity of the user.
     * @param age The age of the user.
     * @param gender The gender of the user.
     * @param maritalStatus The marital status of the user.
     * @param numberOfHousehold The number of individuals in the household.
     * @param annualIncome The annual income of the user.
     * @return A RegisterResponseModel indicating the result of the registration attempt.
     */
    public BaseResponseModel create(String userName, String email, String password1, String password2,
                                     String StateAbb, String PostalCode,
                                     String ethnicity, int age, String gender, String maritalStatus,
                                     int numberOfHousehold, float annualIncome){
        RegistrationData requestModel = new RegistrationData(userName, email, password1, password2, StateAbb,
                PostalCode, ethnicity, age, gender, maritalStatus, numberOfHousehold, annualIncome);
        return inputBoundary.create(requestModel);
    }
}
