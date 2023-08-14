package businessrule;

import businessrule.responsemodel.UserResponseModel;
import driver.screen.UIManager;
import driver.screen.UserUI;

public class UserSession {
    private final UserResponseModel userResponseModel;

    public UserSession(UserResponseModel userResponseModel) {
        this.userResponseModel = userResponseModel;
    }

    public UserResponseModel getUserResponseModel() {
        return userResponseModel;
    }
}
