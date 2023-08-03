package apapter.presenter;

import apapter.responsemodel.HomePageResponseModel;

public interface HomePageOutputBoundary {
    HomePageResponseModel prepareFail(String msg);
    HomePageResponseModel prepareSuccess(HomePageResponseModel response);
}
