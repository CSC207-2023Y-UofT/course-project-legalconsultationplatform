package businessrule.inputboundary;

import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public interface UserLoginInputBoundary {
    HomePageResponseModel login(UserLoginRequestModel requestModel);
}
