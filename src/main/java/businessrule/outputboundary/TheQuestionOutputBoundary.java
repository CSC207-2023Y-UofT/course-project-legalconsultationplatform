package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.UserResponseModel;

public interface TheQuestionOutputBoundary {
    void setControlContainer(ControlContainer controlContainer);
    UserResponseModel prepareFail(String msg);
    UserResponseModel prepareSuccess(UserResponseModel response);
}
