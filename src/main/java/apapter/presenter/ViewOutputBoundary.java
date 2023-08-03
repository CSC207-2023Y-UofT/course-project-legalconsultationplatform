package apapter.presenter;

import apapter.responsemodel.ViewResponseModel;

public interface ViewOutputBoundary {
    ViewResponseModel prepareFail(String msg);
    ViewResponseModel prepareSuccess(ViewResponseModel response);
}
