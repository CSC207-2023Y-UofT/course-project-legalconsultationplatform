package presenter;

import userlogin.LoginResponseModel;
import userlogin.UserLoginInputBoundary;
import userlogin.UserLoginRequestModel;

public class UserLoginControl {
    final UserLoginInputBoundary inputBoundary;

    public UserLoginControl(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    public LoginResponseModel login(int userId, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(userId, password);
        return inputBoundary.login(requestModel);
    }
}
