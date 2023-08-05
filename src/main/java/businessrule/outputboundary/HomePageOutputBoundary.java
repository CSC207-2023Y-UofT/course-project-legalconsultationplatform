package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.HomePageResponseModel;

public interface HomePageOutputBoundary {
    void setControlContainer(ControlContainer controlContainer);
    HomePageResponseModel prepareFail(String msg);
    HomePageResponseModel prepareSuccess(HomePageResponseModel response);
}
