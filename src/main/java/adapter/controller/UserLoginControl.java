package adapter.controller;

import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.UserResponseModel;

/**
 * This class represents the controller responsible for handling user login functionality.
 * It communicates with the business logic through the UserLoginInputBoundary.
 */
public class UserLoginControl {
    private final UserLoginInputBoundary inputBoundary;

    /**
     * Constructs a new UserLoginControl instance with the provided UserLoginInputBoundary.
     *
     * @param inputBoundary The input boundary to communicate with the business logic.
     */
    public UserLoginControl(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    /**
     * Attempts to log in the user with the given userId and password.
     *
     * @param userId The ID of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return A UserResponseModel containing information about the home page after login.
     */
    public UserResponseModel login(int userId, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(userId, password);
        return inputBoundary.login(requestModel);
    }
}
