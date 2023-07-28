package presenter;

public interface LoginOutputBoundary {
    LoginResponseModel prepareFail(String msg);
    LoginResponseModel prepareSuccess(int inputUserId);
}
