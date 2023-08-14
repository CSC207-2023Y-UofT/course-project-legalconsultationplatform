package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;

public interface TheQuestionOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
