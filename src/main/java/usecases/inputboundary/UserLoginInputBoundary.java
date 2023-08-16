package usecases.inputboundary;

import usecases.requests.UserLoginRequestModel;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;

/**
 * This interface provides a method for authenticating user credentials based on the provided login request model.
 */
public interface UserLoginInputBoundary {

    /**
     * Authenticate user credentials based on the provided login request model.
     *
     * @param requestModel The request model containing the user's login credentials.
     * @return A response model indicating the result of the login attempt, typically for updating the home page.
     */
    ViewResponseModel login(UserLoginRequestModel requestModel);
}
