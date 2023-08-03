package businessrule.outputboundary;

import businessrule.responsemodel.ViewResponseModel;

public interface ViewOutputBoundary {
    ViewResponseModel prepareFail(String msg);
    ViewResponseModel prepareSuccess(ViewResponseModel response);
}
