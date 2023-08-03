package adapter.presenter;

import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        return null;
    }

    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        return null;
    }
}
