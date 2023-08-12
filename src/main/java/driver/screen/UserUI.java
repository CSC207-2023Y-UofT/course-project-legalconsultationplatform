package driver.screen;

import adapter.controller.ControlContainer;

public abstract class UserUI extends BaseUI{
    protected String userName;
    protected int userId;
    protected String userType;
    protected String helloMessage;
    protected ControlContainer controlContainer;

    public UserUI(String userName, int userId, ControlContainer controlContainer) {
        this.userName = userName;
        this.userId = userId;
        this.helloMessage = "Hello, " + userName + "(" + userId + ")";
        this.controlContainer = controlContainer;
    }
}
