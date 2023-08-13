package businessrule;

import driver.screen.BaseUI;
import entity.User;

public class UserSession {
    private final int userId;

    public UserSession(int userId) {this.userId = userId;}

    public BaseUI getUI(UIFactory.UIType uiType) {
        return UIFactory.createUI(uiType);
    }

    public int getUserId() {return userId;}
}
