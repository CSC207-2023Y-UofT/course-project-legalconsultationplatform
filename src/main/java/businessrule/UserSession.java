package businessrule;

import driver.screen.BaseUI;
import entity.User;

public class UserSession {
    private User user;

    public UserSession(User user) {
        this.user = user;
    }

    public BaseUI getUI(UIFactory.UIType uiType) {
        return UIFactory.createUI(uiType);
    }
}
