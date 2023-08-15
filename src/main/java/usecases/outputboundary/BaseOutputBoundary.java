package usecases.outputboundary;

import usecases.responses.BaseResponseModel;

public interface BaseOutputBoundary {
    BaseResponseModel prepareSuccess(String msg);
    BaseResponseModel prepareFail(String msg);
}
