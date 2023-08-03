package apapter.presenter;

import apapter.responsemodel.TheQuestionResponseModel;

public interface TheQuestionOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
