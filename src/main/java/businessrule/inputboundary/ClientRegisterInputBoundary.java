package businessrule.inputboundary;

import businessrule.requestmodel.ClientRegisterRequestModel;
import businessrule.responsemodel.RegisterResponseModel;

/**
 * This interface provides a method for creating a client registration based on the provided request model.
 */
public interface ClientRegisterInputBoundary {

     /**
      * Create a client registration based on the provided request model.
      *
      * @param requestModel The request model containing the necessary information for client registration.
      * @return A response model indicating the result of the client registration.
      */
     RegisterResponseModel create(ClientRegisterRequestModel requestModel);
}