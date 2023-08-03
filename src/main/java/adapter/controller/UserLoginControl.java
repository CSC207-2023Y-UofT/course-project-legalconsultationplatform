package adapter.controller;

import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public class UserLoginControl {
    final UserLoginInputBoundary inputBoundary;

    public UserLoginControl(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public HomePageResponseModel login(int userId, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(userId, password);
        return inputBoundary.login(requestModel);
    }
}
