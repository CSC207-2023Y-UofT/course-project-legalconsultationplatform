package businessrule.inputboundary;

import businessrule.responsemodel.RegisterResponseModel;
import businessrule.requestmodel.RegistrationData;

public interface UserRegisterInputBoundary {
    RegisterResponseModel create(RegistrationData requestModel);
}
