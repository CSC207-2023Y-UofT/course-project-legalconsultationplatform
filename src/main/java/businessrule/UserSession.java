package businessrule;

import businessrule.responsemodel.UserResponseModel;
import driver.screen.BaseUI;
import entity.User;

public class UserSession {
    private final UserResponseModel userResponseModel;

    public UserSession(UserResponseModel userResponseModel) {
        this.userResponseModel = userResponseModel;
    }

//    public BaseUI getUI(UIFactory.UIType uiType) {
//        return UIFactory.createUI(uiType);
//    }

    public UserResponseModel getUserResponseModel() {
        return userResponseModel;
    }
}
