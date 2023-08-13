package driver.screen;

import javax.swing.*;
import java.awt.*;

import static driver.screen.UIDesign.setTitleFont;

public abstract class HomePageUI extends UserUI {
    protected String userName;
    protected int userId;
    protected UIManager UIManager;
    protected JLabel title;
    static final String TITLE_NAME = "Home";

    public HomePageUI(String userName, int userId, UIManager UIManager) {
        super(userName, userId, UIManager);

        title = new JLabel(TITLE_NAME);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTitleFont(title);
    }
}
