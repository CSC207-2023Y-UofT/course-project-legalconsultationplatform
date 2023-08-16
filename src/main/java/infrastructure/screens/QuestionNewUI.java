package infrastructure.screens;

import adapters.controllers.ControlContainer;
import adapters.controllers.PostControl;
import infrastructure.screens.utils.UIManager;
import usecases.dto.PostDisplay;
import entities.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static infrastructure.screens.utils.UIDrawer.addSpacer;

/**
 * This class provides a UI for users to compose the initial content of a newly initiated question.
 */
public class QuestionNewUI extends QuestionUI{
    public QuestionNewUI(String userName, int userId, UIManager UIManager, int questionId, String title, String questionType, LocalDate deadline, Map<Integer, PostDisplay> postMap) {
        super(userName, userId, UIManager, questionId, title, questionType, deadline, postMap);

        JPanel spacer = addSpacer(20);

        add(topPanel);
        add(spacer);
        add(inputPost);
        add(spacer);
    }

    @Override
    protected void handlePostAction(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {
        System.out.println("The user post their first reply.");
        try {
            PostControl postControl = controlContainer.getPostControl();
            postControl.createPost(questionId, inputPostArea.getText());
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    protected void handleCloseAction(ControlContainer controlContainer, JPanel screens, CardLayout cardLayout) {

    }
}
