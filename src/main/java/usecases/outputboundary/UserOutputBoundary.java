package usecases.outputboundary;

import usecases.responses.UserResponseModel;

public interface UserOutputBoundary {
    UserResponseModel prepareFail(String msg);
    UserResponseModel prepareSuccess(UserResponseModel response);
}
