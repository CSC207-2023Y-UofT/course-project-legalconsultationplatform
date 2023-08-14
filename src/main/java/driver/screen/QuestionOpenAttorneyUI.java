package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static driver.screen.UIDrawer.*;

public class QuestionOpenAttorneyUI extends QuestionUI {

    public QuestionOpenAttorneyUI(String userName, int userId, UIManager UIManager, String title,
                                  String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId, UIManager, title, questionType, deadline, postMap);

        //spacers
        JPanel spacer = addSpacer(20);

        //Add everything together
        add(topPanel);
        add(spacer);
        add(inputPost);
        add(spacer);
        add(backAndHomepage);
    }

    @Override
    protected void handlePostAction(ControlContainer controlContainer, JPanel screens,
                                    CardLayout cardLayout) {
        System.out.println("The user wants to post a reply.");
        try {
            PostControl postControl = controlContainer.getPostControl();
            postControl.createPost(questionId, inputPostArea.getText());
        } catch (ApplicationException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }

    @Override
    protected void handleCloseAction(ControlContainer controlContainer, JPanel screens,
                                     CardLayout cardLayout) {
    }
}
