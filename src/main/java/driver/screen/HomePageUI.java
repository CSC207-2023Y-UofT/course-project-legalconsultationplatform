package driver.screen;

import javax.swing.*;
import java.awt.*;

import static driver.screen.UIDesign.setTitleFont;

public abstract class HomePageUI extends UserUI {
    protected JLabel title;
    static final String TITLE_NAME = "Home";
    static final String LOG_OUT_BUTTON_NAME = "Log Out";
    static final String VIEW_QUESTION_HISTORY_BUTTON_NAME = "View question history";

    public HomePageUI(String userName, int userId, UIManager UIManager) {
        super(userName, userId, UIManager);

        title = new JLabel(TITLE_NAME);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        setTitleFont(title);
    }
}
