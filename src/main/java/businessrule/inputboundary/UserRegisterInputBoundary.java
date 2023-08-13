package businessrule.inputboundary;

import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.BaseResponseModel;
import businessrule.responsemodel.UserResponseModel;

public interface UserRegisterInputBoundary {
    BaseResponseModel create(RegistrationData requestModel);
}
