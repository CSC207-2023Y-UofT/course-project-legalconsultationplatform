package adapters.controllers;

import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.requests.RegistrationData;
import usecases.responses.BaseResponseModel;

/**
 * This class represents responsible for handling user registration.
 */
public class RegisterControl {
    private final UserRegisterInputBoundary inputBoundary;

    /**
     * Constructs a new instance of ClientRegisterControl.
     *
     * @param inputBoundary The input boundary for user registration.
     */
    public RegisterControl(UserRegisterInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Creates a new user registration based on the provided information.
     *
     * @param requestModel @return A RegisterResponseModel indicating the result of the registration attempt.
     */
    public BaseResponseModel create(RegistrationData requestModel){
        return inputBoundary.create(requestModel);
    }
}
