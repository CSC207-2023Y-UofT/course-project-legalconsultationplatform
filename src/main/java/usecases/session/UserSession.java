package usecases.session;

import usecases.responses.UserResponseModel;

/**
 * This class represents a session for a specific user.
 * It stores key user information and carries over it once the user login.
 */
public class UserSession {
    private final UserResponseModel userResponseModel;

    public UserSession(UserResponseModel userResponseModel) {
        this.userResponseModel = userResponseModel;
    }

    public UserResponseModel getUserResponseModel() {
        return userResponseModel;
    }
}
