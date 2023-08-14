package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.UserResponseModel;

public interface UserOutputBoundary {
    UserResponseModel prepareFail(String msg);
    UserResponseModel prepareSuccess(UserResponseModel response);
}
