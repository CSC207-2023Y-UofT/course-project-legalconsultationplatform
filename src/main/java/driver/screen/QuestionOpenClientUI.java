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
