package driver.screen;

import adapter.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static driver.screen.UIDrawer.*;



public class ClientHomePageUI extends HomePageUI implements ActionListener {
    static final String ASK_NEW_QUESTION_BUTTON_NAME = "Ask new question";
    public ClientHomePageUI(String userName, int userId, UIManager UIManager) {
        super(userName, userId, UIManager);

        //Spacers
        JPanel topSpacer = addSpacer(50);

        //Buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(ASK_NEW_QUESTION_BUTTON_NAME);
        buttonList.add(VIEW_QUESTION_HISTORY_BUTTON_NAME);
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
            case ASK_NEW_QUESTION_BUTTON_NAME:
                System.out.println("Client chooses to ask new question.");
                AskQuestionUI askQuestionUI = new AskQuestionUI(userName, userId, uiManager);
                screens.add(askQuestionUI, "askQuestion");
                cardLayout.show(screens, "askQuestion");
                break;

            case VIEW_QUESTION_HISTORY_BUTTON_NAME:
                System.out.println("Client chooses to view question history.");
                ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
                viewQuestionControl.viewQuestion();
                break;

            case LOG_OUT_BUTTON_NAME:
                WelcomeUI welcomeUI = new WelcomeUI(uiManager);
                screens.add(welcomeUI, "Welcome");
                cardLayout.show(screens, "Welcome");
                break;
        }
    }
}
