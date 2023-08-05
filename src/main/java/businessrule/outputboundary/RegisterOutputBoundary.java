package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.RegisterResponseModel;

public interface RegisterOutputBoundary {
    void setControlContainer(ControlContainer controlContainer);
    RegisterResponseModel prepareSuccess(String msg);
    RegisterResponseModel prepareFail(String msg);
}
