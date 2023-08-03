package businessrule.inputboundary;

import apapter.LoginResponseModel;
import businessrule.requestmodel.UserLoginRequestModel;

public interface UserLoginInputBoundary {
    LoginResponseModel login(UserLoginRequestModel requestModel);
}
