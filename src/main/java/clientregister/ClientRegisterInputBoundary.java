package clientregister;

import presenter.MessageResponseModel;

public interface ClientRegisterInputBoundary {
    MessageResponseModel create(ClientRegisterRequestModel requestModel);
}
