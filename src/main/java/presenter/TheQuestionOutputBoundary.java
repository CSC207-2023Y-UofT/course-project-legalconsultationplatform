package presenter;

public interface TheQuestionOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);

    // TODO: complete method signature
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
