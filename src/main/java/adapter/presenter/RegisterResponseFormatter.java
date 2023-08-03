package adapter.presenter;

import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;

public class RegisterResponseFormatter implements RegisterOutputBoundary {
    @Override
    public RegisterResponseModel prepareSuccess(String msg) {
        return null;
    }

    @Override
    public RegisterResponseModel prepareFail(String msg) {
        return null;
    }
}
