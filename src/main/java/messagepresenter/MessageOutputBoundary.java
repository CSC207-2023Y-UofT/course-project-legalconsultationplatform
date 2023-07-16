package messagepresenter;

public interface MessageOutputBoundary {
    MessageResponseModel prepareFail(String msg);
    MessageResponseModel prepareSuccess(String msg);
}
