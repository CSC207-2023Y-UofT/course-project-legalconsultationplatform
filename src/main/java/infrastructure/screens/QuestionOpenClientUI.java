package infrastructure.screens;

import adapters.controllers.CloseQuestionControl;
import adapters.controllers.ControlContainer;
import infrastructure.screens.utils.UIManager;
import usecases.dto.PostDisplay;
import entities.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This class provides a UI for client to their follow-up.
 */
public class QuestionOpenClientUI extends QuestionOpenAttorneyUI{

    public QuestionOpenClientUI(String userName, int userId, UIManager UIManager, int questionId, String title,
                                String questionType, LocalDate deadline, Map<Integer, PostDisplay> postMap) {
        super(userName, userId ,UIManager,questionId, title, questionType, deadline, postMap);

        //The spacer
        JPanel spacer2 = addSpacer(30);

        ///Add everything together
        add(topPanel);
        add(spacer2);
        add(inputPost);
        add(spacer2);
        add(closeButton);
        add(backAndHomepage);
        add(spacer2);
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
