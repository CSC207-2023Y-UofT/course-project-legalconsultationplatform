package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClientHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    int userId;
    String userName;
    CardLayout cardLayout;
    JPanel screens;
    public ClientHomePageUI(ControlContainer controlContainer, int userId, String userName,
                            CardLayout cardLayout, JPanel screens) {
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
        JButton ratePastQuestions = new JButton("Rate past questions");

        buttons.add(askNewQuestion);
        buttons.add(viewQuestionHistory);
        buttons.add(ratePastQuestions);
        askNewQuestion.addActionListener(this);
        viewQuestionHistory.addActionListener(this);
        ratePastQuestions.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(helloMessage)
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Ask new question".equals(actionCommand)){
            AskQuestionUI askQuestionUI = new AskQuestionUI();
            screens.add(askQuestionUI, "askQuestion");
            cardLayout.show(screens, "askQuestion");
            System.out.println("Ask question page showed");
        } else if ("View question history".equals(actionCommand)){
            QuestionListUI questionHistoryUI = new QuestionListUI();
            screens.add(questionHistoryUI, "questionHistory");
            cardLayout.show(screens, "questionHistory");
            System.out.println("Question history showed");
        } else if ("Rate past questions".equals(actionCommand)){
            QuestionListUI rateQuestionUI = new QuestionListUI();
            screens.add(rateQuestionUI, "rateQuestion");
            cardLayout.show(screens, "rateQuestion");
            System.out.print("Rate questions showed");
        }
    }
}
