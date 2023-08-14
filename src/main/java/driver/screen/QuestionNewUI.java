package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static driver.screen.UIDrawer.addSpacer;

public class QuestionNewUI extends QuestionUI{
    public QuestionNewUI(String userName, int userId, UIManager UIManager, int questionId, String title, String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
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
