package clientregister;

import messagepresenter.MessageResponseModel;

public interface ClientRegisterInputBoundary {
    MessageResponseModel create(ClientRegisterRequestModel requestModel);
}
