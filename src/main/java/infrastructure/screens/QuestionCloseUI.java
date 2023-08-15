package infrastructure.screens;
import adapters.controllers.ControlContainer;
import infrastructure.screens.utils.UIManager;
import usecases.dto.PostDisplay;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This class provides a UI for client to view the question that was closed.
 */
public class QuestionCloseUI extends QuestionUI{
    public QuestionCloseUI(String userName, int userId, UIManager UIManager, int questionId, String title,
                           String questionType, LocalDate deadline, Map<Integer, PostDisplay> postMap) {
        super(userName, userId, UIManager, questionId, title, questionType, deadline, postMap);

        //Spacers
        JPanel spacer = addSpacer(50);
        JPanel spacer2 = addSpacer(20);

        add(topPanel);
        add(spacer2);
        add(backAndHomepage);
        add(spacer);
    }

    @Override
    protected void handlePostAction(ControlContainer controlContainer, JPanel screens,
                                    CardLayout cardLayout) {
    }

    @Override
    protected void handleCloseAction(ControlContainer controlContainer, JPanel screens,
                                     CardLayout cardLayout) {
    }
}
