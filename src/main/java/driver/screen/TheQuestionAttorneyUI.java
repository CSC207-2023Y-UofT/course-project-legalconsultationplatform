package driver.screen;

import adapter.controller.ControlContainer;
import businessrule.usecase.util.PostDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.Map;

import static driver.screen.UIDesign.lightGreenColor;

public class TheQuestionAttorneyUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    int questionId;
    String title;
    String type;
    LocalDate deadline;
    Map<Integer, PostDisplayFormatter> postMap;

    public TheQuestionAttorneyUI(ControlContainer controlContainer, CardLayout cardLayout,
                              JPanel screens, int userId, String userName, int questionId, String title,
                              String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;
        this.questionId = questionId;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.postMap = postMap;

        //Top half of the panel
        JPanel topPanel = new TheQuestionTopPanel(userId, userName, questionId, title, type, deadline, postMap);

        JPanel spacer = UIDesign.addSpacer(30);

        JButton homePage = new JButton("Home Page");
        UIDesign.setGeneralButton(homePage);
        homePage.setAlignmentX(CENTER_ALIGNMENT);
        homePage.addActionListener(this);

        //Add everything together
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(lightGreenColor);
        add(topPanel);
        add(spacer);
        add(homePage);

    }
    public void actionPerformed(ActionEvent e) {
        AttorneyHomePageUI homePageUI = new AttorneyHomePageUI(controlContainer, cardLayout,
                    screens, userId, userName);
            screens.add(homePageUI, "Home Page");
            cardLayout.show(screens, "Home Page");
        }
    }
