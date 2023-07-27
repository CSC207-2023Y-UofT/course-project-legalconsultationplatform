package presenter;

public interface MessageOutputBoundary {
    MessageResponseModel prepareFail(String msg);
  
    // TODO: complete method signature
    MessageResponseModel prepareSuccess(String msg);
}
