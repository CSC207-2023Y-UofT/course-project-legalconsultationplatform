package businessrule.inputboundary;

import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.RegisterResponseModel;

public interface ClientRegisterInputBoundary {
     RegisterResponseModel create(ClientRegisterRequestModel requestModel);
}
