package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.BaseResponseModel;

public interface BaseOutputBoundary {
    BaseResponseModel prepareSuccess(String msg);
    BaseResponseModel prepareFail(String msg);
}
