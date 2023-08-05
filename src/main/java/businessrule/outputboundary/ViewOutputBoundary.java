package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.ViewResponseModel;

public interface ViewOutputBoundary {
    void setControlContainer(ControlContainer controlContainer);
    ViewResponseModel prepareFail(String msg);
    ViewResponseModel prepareSuccess(ViewResponseModel response);
}
