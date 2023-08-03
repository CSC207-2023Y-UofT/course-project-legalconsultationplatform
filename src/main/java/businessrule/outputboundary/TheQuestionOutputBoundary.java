package businessrule.outputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;

public interface TheQuestionOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
