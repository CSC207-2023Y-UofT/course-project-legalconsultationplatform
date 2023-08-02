package presenter;

import clientregister.ClientRegisterResponseModel;

public interface ClientRegisterOutputBoundary {
    ClientRegisterResponseModel prepareFail(String msg);
    ClientRegisterResponseModel prepareSuccess(ClientRegisterResponseModel response);
}

