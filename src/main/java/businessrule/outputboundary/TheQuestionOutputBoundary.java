package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.TheQuestionResponseModel;

public interface TheQuestionOutputBoundary {
    void setControlContainer(ControlContainer controlContainer);
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
