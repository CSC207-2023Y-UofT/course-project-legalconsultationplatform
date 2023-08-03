package adapter.presenter;

import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;

public class ViewResponseFormatter implements ViewOutputBoundary {
    @Override
    public ViewResponseModel prepareFail(String msg) {
        return null;
    }

    @Override
    public ViewResponseModel prepareSuccess(ViewResponseModel response) {
        return null;
    }
}
