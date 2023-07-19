package userlogin;

import presenter.LoginResponseModel;

public class UserLoginControl {
    final UserLoginInputBoundary inputBoundary;

    public UserLoginControl(UserLoginInputBoundary inputBoundary) {
        this.inputBoundary = inputBoundary;
    }

    LoginResponseModel login(int userId, String password){
        UserLoginRequestModel requestModel = new UserLoginRequestModel(userId, password);
        return inputBoundary.login(requestModel);
    }
}
