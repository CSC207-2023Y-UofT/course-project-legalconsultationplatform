package businessrule.outputboundary;

import businessrule.responsemodel.RegisterResponseModel;

public interface RegisterOutputBoundary {
    RegisterResponseModel prepareSuccess(RegisterResponseModel response);
    RegisterResponseModel prepareFail(String msg);
}
