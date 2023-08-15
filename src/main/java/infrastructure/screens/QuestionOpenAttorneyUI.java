package infrastructure.screens;

import infrastructure.screens.utils.UIManager;
import usecases.dto.PostDisplay;

import javax.swing.*;
import java.time.LocalDate;
import java.util.Map;

import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This class provides a UI for attorney to post their answer for a specific question.
 */
public class QuestionOpenAttorneyUI extends QuestionNewUI {

    public QuestionOpenAttorneyUI(String userName, int userId, UIManager UIManager, int questionId, String title,
                                  String questionType, LocalDate deadline, Map<Integer, PostDisplay> postMap) {
        super(userName, userId, UIManager, questionId, title, questionType, deadline, postMap);

        //spacers
        JPanel spacer = addSpacer(20);

        //Add everything together
        add(topPanel);
        add(spacer);
        add(inputPost);
        add(spacer);
        add(backAndHomepage);
        add(spacer);
    }
}
