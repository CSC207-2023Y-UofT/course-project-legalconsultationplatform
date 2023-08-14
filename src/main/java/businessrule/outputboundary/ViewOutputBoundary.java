package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import entity.User;

public interface ViewOutputBoundary {
    ViewResponseModel prepareFail(String msg);
    ViewResponseModel prepareSuccess(ViewResponseModel response);
}
