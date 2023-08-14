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

public class QuestionOpenAttorneyUI extends QuestionNewUI {

    public QuestionOpenAttorneyUI(String userName, int userId, UIManager UIManager, int questionId, String title,
                                  String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
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
