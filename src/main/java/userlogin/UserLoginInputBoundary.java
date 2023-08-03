package userlogin;

import apapter.LoginResponseModel;

public interface UserLoginInputBoundary {
    LoginResponseModel login(UserLoginRequestModel requestModel);
}
