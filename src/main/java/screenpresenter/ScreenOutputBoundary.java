package screenpresenter;

public interface ScreenOutputBoundary {
    ScreenResponseModel prepareSuccess();

    ScreenResponseModel prepareFailure();
}
