package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static javax.swing.JOptionPane.showMessageDialog;

public class AttorneyHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    int userId;
    String userName;
    CardLayout cardLayout;
    JPanel screens;
    public AttorneyHomePageUI(ControlContainer controlContainer, int userId,
                              String userName, CardLayout cardLayout, JPanel screens) {
        this.controlContainer = controlContainer;
        this.userId = userId;
        this.userName = userName;
        this.cardLayout = cardLayout;
        this.screens = screens;
        //The title
        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //The userName and userId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        //The three buttons
        JPanel buttons = new JPanel();
        JButton browseQuestions = new JButton("Browse available questions");
        JButton viewQuestionHistory = new JButton("View question history");
        JButton recommendation = new JButton("Recommended questions");

        buttons.add(browseQuestions);
        buttons.add(viewQuestionHistory);
        buttons.add(recommendation);
        browseQuestions.addActionListener(this);
        viewQuestionHistory.addActionListener(this);
        recommendation.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(helloMessage)
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Browse available questions".equals(actionCommand)){
            QuestionListUI browseQuestionsUI = new QuestionListUI();
            screens.add(browseQuestionsUI, "browseQuestion");
            cardLayout.show(screens, "browseQuestion");
            System.out.println("Available questions showed");
        } else if ("View question history".equals(actionCommand)){
            QuestionListUI questionHistoryUI = new QuestionListUI();
            screens.add(questionHistoryUI, "questionHistory");
            cardLayout.show(screens, "questionHistory");
            System.out.println("Question history showed");
        } else if ("Recommended questions".equals(actionCommand)){
            QuestionListUI recommendedQuestionUI = new QuestionListUI();
            screens.add(recommendedQuestionUI, "recommendedQuestion");
            cardLayout.show(screens, "recommendedQuestion");
            System.out.print("Recommended question showed");
        }
    }
}
