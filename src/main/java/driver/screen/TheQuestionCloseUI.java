package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.PostControl;
import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.QuestionDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;

public class TheQuestionCloseUI extends QuestionUI implements ActionListener {
    protected String userName;
    protected int userId;
    protected JPanel helloMessage;
    protected UIManager UIManager;
    JPanel questionTitle;
    JPanel previousDiscussions;
    JScrollPane postScrollPane;
    Map<Integer, PostDisplayFormatter> postMap;

    public TheQuestionCloseUI(String userName, int userId, UIManager UIManager, String title,
                              String questionType, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        super(userName, userId, UIManager, title, questionType, deadline, postMap);

        //spacers
        JPanel spacer = addSpacer(30);
        JPanel spacer2 = addSpacer(20);

        //Buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(BACK_BUTTON_NAME);
        buttonList.add(HOME_PAGE_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);

        //Add everything together
        add(helloMessage);
        add(spacer2);
        add(questionTitle);
        add(spacer2);
        add(previousDiscussions);
        add(postScrollPane);
        add(spacer);
        add(buttons);
    }

    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel screens = UIManager.getScreens();
        if (HOME_PAGE_BUTTON_NAME.equals(actionCommand)) {
            cardLayout.show(screens, "Home Page");
        } else if (BACK_BUTTON_NAME.equals(actionCommand)){
            cardLayout.show(screens, "Question List");
        }
    }
}
