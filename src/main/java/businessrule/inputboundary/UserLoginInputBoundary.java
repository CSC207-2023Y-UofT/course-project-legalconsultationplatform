package businessrule.inputboundary;

import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface UserLoginInputBoundary {
    UserResponseModel login(UserLoginRequestModel requestModel);
}
