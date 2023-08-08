package driver.screen;

import adapter.controller.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

        JLabel title = new JLabel("Client Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        JPanel buttons = new JPanel();
        JButton askNewQuestion = new JButton("Ask new question");
        JButton viewQuestionHistory = new JButton("View question history");

        buttons.add(askNewQuestion);
        buttons.add(viewQuestionHistory);
        askNewQuestion.addActionListener(this);
        viewQuestionHistory.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(helloMessage);
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
        }
    }
}
