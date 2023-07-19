package presenter;

public interface TheQuestionOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess(String msg);
}
