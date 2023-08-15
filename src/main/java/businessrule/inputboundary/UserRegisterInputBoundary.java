package businessrule.inputboundary;

import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.BaseResponseModel;
import businessrule.responsemodel.UserResponseModel;

/**
 * This interface represents the input boundary for user registration use case.
 */
public interface UserRegisterInputBoundary {

    /**
     * Creates a user registration using the provided registration data.
     *
     * @param requestModel The registration data for the user.
     * @return The response model indicating the result of the registration process.
     */
    BaseResponseModel create(RegistrationData requestModel);
}
