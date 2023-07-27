package presenter;

public interface ViewOutputBoundary {
    ViewResponseModel prepareFail(String msg);
  
    // TODO: complete method signature
    ViewResponseModel prepareSuccess();
}
