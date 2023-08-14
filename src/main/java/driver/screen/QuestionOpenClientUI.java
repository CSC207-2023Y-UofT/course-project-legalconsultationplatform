package driver.screen;

import adapter.controller.CloseQuestionControl;
import adapter.controller.ControlContainer;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static driver.screen.UIDrawer.*;

public class QuestionOpenClientUI extends QuestionOpenAttorneyUI{
    protected String userName;
    protected int userId;
    protected UIManager UIManager;
    int questionId;
    JPanel topPanel;
    JPanel backAndHomepage;
    JPanel inputPost;
    JPanel closeButton;
    JTextArea inputPostArea = new JTextArea(3, 30);
    static final String BACK_BUTTON_NAME = "Back";
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";
    static final String NEW_REPLY_PLACEHOLDER = "Type your content here...";
    static final String POST_BUTTON_NAME = "Post";
    static final String CLOSE_BUTTON_NAME = "Close Question";

    public QuestionOpenClientUI(String userName, int userId, UIManager UIManager, String title,
                                String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId ,UIManager,title, questionType, deadline, postMap);

        //The spacer
        JPanel spacer = addSpacer(20);
        JPanel spacer2 = addSpacer(30);

        ///Add everything together
        add(topPanel);
        add(spacer2);
        add(inputPost);
        add(spacer2);
        add(closeButton);
        add(backAndHomepage);
    }

    @Override
    protected void handleCloseAction(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {
        System.out.println("Client wants to close the question.");
        try {
            CloseQuestionControl closeQuestionControl = controlContainer.getCloseQuestionControl();
            closeQuestionControl.closeQuestion(questionId);
            JFrame rateFrame = new JFrame("Rate this question");
            RatePanel ratePanel = new RatePanel(controlContainer, rateFrame, userId, questionId);
            rateFrame.add(ratePanel);
            rateFrame.pack();
            rateFrame.setVisible(true);
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }
}
