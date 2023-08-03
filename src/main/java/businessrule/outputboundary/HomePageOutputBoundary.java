package businessrule.outputboundary;

import businessrule.responsemodel.HomePageResponseModel;

public interface HomePageOutputBoundary {
    HomePageResponseModel prepareFail(String msg);
    HomePageResponseModel prepareSuccess(HomePageResponseModel response);
}
