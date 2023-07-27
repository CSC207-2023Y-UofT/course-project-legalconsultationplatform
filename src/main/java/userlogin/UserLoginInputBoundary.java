package userlogin;

import presenter.LoginResponseModel;

public interface UserLoginInputBoundary {
    LoginResponseModel login(UserLoginRequestModel requestModel);
}
