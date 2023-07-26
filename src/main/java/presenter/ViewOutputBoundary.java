package presenter;

public interface ViewOutputBoundary {
    ViewResponseModel prepareFail(String msg);
    ViewResponseModel prepareSuccess();
}
