package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.ViewQuestionControl;
import cn.hutool.core.net.LocalPortGenerater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDrawer.*;

public class AttorneyHomePageUI extends HomePageUI implements ActionListener {
    protected String userName;
    protected int userId;
    protected JPanel helloMessage;
    protected UIManager UIManager;
    protected JLabel title;
    static final String BROWSE_AVAILABLE_QUESTIONS_BUTTON_NAME = "Ask new question";
    static final String VIEW_QUESTION_HISTORY_BUTTON_NAME = "View question history";
    static final String RECOMMENDED_QUESTIONS_BUTTON_NAME = "Recommended questions";
    static final String LOG_OUT_BUTTON_NAME = "Log Out";
    public AttorneyHomePageUI(String userName, int userId, UIManager UIManager) {
        super(userName, userId, UIManager);

        //Spacer
        JPanel topSpacer = addSpacer(50);

        //Buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(BROWSE_AVAILABLE_QUESTIONS_BUTTON_NAME);
        buttonList.add(VIEW_QUESTION_HISTORY_BUTTON_NAME);
        buttonList.add(RECOMMENDED_QUESTIONS_BUTTON_NAME);
        buttonList.add(LOG_OUT_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(300, 50), 50, this);


        add(helloMessage);
        add(topSpacer);
        add(title);
        add(topSpacer);
        add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        ControlContainer controlContainer = UIManager.getControlContainer();
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        switch (actionCommand) {
            case BROWSE_AVAILABLE_QUESTIONS_BUTTON_NAME:
                ViewQuestionControl browseQuestionControl = controlContainer.getBrowseQuestionControl();
                browseQuestionControl.viewQuestion(userId);
                break;

            case VIEW_QUESTION_HISTORY_BUTTON_NAME:
                ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
                viewQuestionControl.viewQuestion(userId);
                break;

            case RECOMMENDED_QUESTIONS_BUTTON_NAME:
                ViewQuestionControl recommendationControl = controlContainer.getRecommendationControl();
                recommendationControl.viewQuestion(userId);
                break;

            case LOG_OUT_BUTTON_NAME:
                WelcomeUI welcomeUI = new WelcomeUI(UIManager);
                screens.add(welcomeUI, "Welcome");
                cardLayout.show(screens, "Welcome");
                break;
        }
    }
}
