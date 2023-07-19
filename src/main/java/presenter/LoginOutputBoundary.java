package presenter;

public interface LoginOutputBoundary {
    TheQuestionResponseModel prepareFail(String msg);
    TheQuestionResponseModel prepareSuccess();
}
