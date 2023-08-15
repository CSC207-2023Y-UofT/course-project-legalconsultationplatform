package usecases.inputboundary;

import usecases.requests.RegistrationData;
import usecases.responses.BaseResponseModel;

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
