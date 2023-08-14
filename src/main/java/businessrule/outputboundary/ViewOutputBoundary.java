package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import entity.User;

public interface ViewOutputBoundary {
    UserResponseModel prepareFail(String msg);
    UserResponseModel prepareSuccess(UserResponseModel response);
}
