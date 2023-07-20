package attorneybrowsequestion;

import screenpresenter.ScreenResponseModel;

public interface BrowseInputBoundary {
    public ScreenResponseModel prepareFail(String msg);
    public ScreenResponseModel prepareSuccess();

    BrowseRespondModel browseQuestion(BrowseRequestModel browseRequestModel);
}
