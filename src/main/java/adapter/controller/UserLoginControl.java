package adapter.controller;

import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.UserResponseModel;

public class UserLoginControl {
    private final UserLoginInputBoundary inputBoundary;

    public UserLoginControl(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public UserResponseModel login(int userId, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(userId, password);
        return inputBoundary.login(requestModel);
    }
}
