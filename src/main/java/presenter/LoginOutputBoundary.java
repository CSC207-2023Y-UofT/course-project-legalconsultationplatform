package presenter;

import userlogin.LoginResponseModel;

public interface LoginOutputBoundary {
    LoginResponseModel prepareFail(String msg);
    LoginResponseModel prepareSuccess(LoginResponseModel response);
}
