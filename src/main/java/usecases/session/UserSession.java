package usecases.session;

import usecases.responses.ViewResponseModel;

/**
 * This class represents a session for a specific user.
 * It stores key user information and carries over it once the user login.
 */
public class UserSession {
    private final ViewResponseModel viewResponseModel;

    public UserSession(ViewResponseModel viewResponseModel) {
        this.viewResponseModel = viewResponseModel;
    }

    public ViewResponseModel getUserResponseModel() {
        return viewResponseModel;
    }
}
