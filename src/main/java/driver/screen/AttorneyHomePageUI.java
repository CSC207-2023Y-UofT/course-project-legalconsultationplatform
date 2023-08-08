package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.ViewQuestionControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AttorneyHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    public AttorneyHomePageUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens,
                              int userId, String userName) {
        this.controlContainer = controlContainer;
        this.userId = userId;
        this.userName = userName;
        this.cardLayout = cardLayout;
        this.screens = screens;

        JPanel helloMessage = UIDesign.helloMessageConstructor(userName, userId);

        setBackground(UIDesign.lightGreenColor);
        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);

        JPanel topSpacer = UIDesign.addSpacer(50);
        JPanel middleSpacer = UIDesign.addSpacer(50);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton browseAvailableQuestions = new JButton("Browse available questions");
        JButton viewQuestionHistory = new JButton("View question history");
        JButton recommendedQuestions = new JButton("Recommended questions");
        JButton logOut = new JButton("Log Out");
        JPanel spacer = UIDesign.addSpacer(50);
        JPanel spacer2 = UIDesign.addSpacer(50);
        JPanel spacer3 = UIDesign.addSpacer(50);

        UIDesign.setHomePageButton(browseAvailableQuestions);
        UIDesign.setHomePageButton(viewQuestionHistory);
        UIDesign.setHomePageButton(recommendedQuestions);
        UIDesign.setGeneralButton(logOut);
        buttons.add(browseAvailableQuestions);
        buttons.add(spacer);
        buttons.add(viewQuestionHistory);
        buttons.add(spacer2);
        buttons.add(recommendedQuestions);
        buttons.add(spacer3);
        buttons.add(logOut);

        browseAvailableQuestions.addActionListener(this);
        viewQuestionHistory.addActionListener(this);
        recommendedQuestions.addActionListener(this);
        logOut.addActionListener(this);

        setLayout(new BoxLayout(this, Y_AXIS));
        add(helloMessage);
        add(topSpacer);
        add(title);
        add(middleSpacer);
        add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Browse available questions".equals(actionCommand)){
            ViewQuestionControl browseQuestionControl = controlContainer.getBrowseQuestionControl();
            browseQuestionControl.viewQuestion(userId);
        } else if ("View question history".equals(actionCommand)){
            ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
            viewQuestionControl.viewQuestion(userId);
        } else if ("Recommended questions".equals(actionCommand)){
            ViewQuestionControl recommendationControl = controlContainer.getRecommendationControl();
            recommendationControl.viewQuestion(userId);
        } else if ("Log Out".equals(actionCommand)){
            WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
            screens.add(welcomeUI, "Welcome");
            cardLayout.show(screens, "Welcome");
        }
    }
}
