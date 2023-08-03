package businessrule.outputboundary;

import businessrule.responsemodel.RegisterResponseModel;

public interface RegisterOutputBoundary {
    RegisterResponseModel prepareSuccess(String msg);
    RegisterResponseModel prepareFail(String msg);
}
