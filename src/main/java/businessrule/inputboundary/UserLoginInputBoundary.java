package businessrule.inputboundary;

import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.UserResponseModel;

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
    UserResponseModel login(UserLoginRequestModel requestModel);
}
