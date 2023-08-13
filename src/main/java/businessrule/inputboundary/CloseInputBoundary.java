package businessrule.inputboundary;

import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface CloseInputBoundary {
    UserResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
