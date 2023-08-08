package driver.screen;

import adapter.controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;

import static javax.swing.BoxLayout.*;

public class ClientHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    public ClientHomePageUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens,
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

        JPanel topSpacer = UIDesign.addSpacer(80);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton askNewQuestion = new JButton("Ask new question");
        JPanel spacer = UIDesign.addSpacer(50);
        JPanel spacer2 = UIDesign.addSpacer(50);
        JButton viewQuestionHistory = new JButton("View question history");
        JButton logOut = new JButton("Log Out");

        UIDesign.setHomePageButton(askNewQuestion);
        UIDesign.setHomePageButton(viewQuestionHistory);
        UIDesign.setGeneralButton(logOut);
        buttons.add(askNewQuestion);
        buttons.add(spacer);
        buttons.add(viewQuestionHistory);
        buttons.add(spacer2);
        buttons.add(logOut);
        askNewQuestion.addActionListener(this);
        viewQuestionHistory.addActionListener(this);
        logOut.addActionListener(this);

        this.setLayout(new BoxLayout(this, Y_AXIS));
        this.add(helloMessage);
        this.add(topSpacer);
        this.add(title);
        this.add(topSpacer);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Ask new question".equals(actionCommand)){
            System.out.println("Client chooses to ask new question.");
            AskQuestionUI askQuestionUI = new AskQuestionUI(controlContainer, cardLayout, screens, userId, userName);
            screens.add(askQuestionUI, "askQuestion");
            cardLayout.show(screens, "askQuestion");
        } else if ("View question history".equals(actionCommand)){
            System.out.println("Client chooses to view question history.");
            ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
            viewQuestionControl.viewQuestion(userId);
        } else if ("Log Out".equals(actionCommand)){
            WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
            screens.add(welcomeUI, "Welcome");
            cardLayout.show(screens, "Welcome");
        }
    }
}
