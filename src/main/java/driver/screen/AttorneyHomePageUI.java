package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.ViewQuestionControl;
import businessrule.SessionManager;
import businessrule.UIFactory;
import cn.hutool.core.net.LocalPortGenerater;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDrawer.*;

public class AttorneyHomePageUI extends HomePageUI implements ActionListener {
    static final String BROWSE_AVAILABLE_QUESTIONS_BUTTON_NAME = "Browse Available Questions";
    static final String RECOMMENDED_QUESTIONS_BUTTON_NAME = "Recommended Questions";
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
        ControlContainer controlContainer = uiManager.getControlContainer();
        JPanel screens = uiManager.getScreens();
        CardLayout cardLayout = uiManager.getCardLayout();
        switch (actionCommand) {
            case BROWSE_AVAILABLE_QUESTIONS_BUTTON_NAME:
                ViewQuestionControl browseQuestionControl = controlContainer.getBrowseQuestionControl();
                browseQuestionControl.viewQuestion();
                break;

            case VIEW_QUESTION_HISTORY_BUTTON_NAME:
                ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
                viewQuestionControl.viewQuestion();
                break;

            case RECOMMENDED_QUESTIONS_BUTTON_NAME:
                ViewQuestionControl recommendationControl = controlContainer.getRecommendationControl();
                recommendationControl.viewQuestion();
                break;

            case LOG_OUT_BUTTON_NAME:
                WelcomeUI welcomeUI = new WelcomeUI(uiManager);
                screens.add(welcomeUI, "Welcome");
                cardLayout.show(screens, "Welcome");
                SessionManager.clearSession();
                UIFactory.clearUIFactory();
                break;
        }
    }
}
