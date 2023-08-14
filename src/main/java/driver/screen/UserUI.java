package driver.screen;

import javax.swing.*;
import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;

public abstract class UserUI extends BaseUI{
    protected String userName;
    protected int userId;
    protected JPanel helloMessage;

    public UserUI(String userName, int userId, UIManager UIManager) {
        super(UIManager);
        this.userName = userName;
        this.userId = userId;
        this.helloMessage = helloMessageConstructor(userName, userId);
    }
}
