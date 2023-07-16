package screenpresenter;

public interface ScreenOutputBoundary {
    public ScreenResponseModel prepareFail(String msg);
    public ScreenResponseModel prepareSuccess();
}
