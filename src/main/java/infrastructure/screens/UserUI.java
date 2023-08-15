package infrastructure.screens;

import infrastructure.screens.utils.UIManager;

import javax.swing.*;

import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This abstract class serve as the base for all user-specific UI.
 */
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
