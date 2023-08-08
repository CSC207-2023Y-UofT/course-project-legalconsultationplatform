package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.SelectQuestionControl;
import businessrule.usecase.util.QuestionDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class QuestionListUI extends JPanel{
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    Map<Integer, QuestionDisplayFormatter> questionMap;

    public QuestionListUI(ControlContainer controlContainer, CardLayout cardLayout,
                          JPanel screens, int userId, String userName,
                          Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;
        this.questionMap = questionMap;

        //UserName and UserId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        //The scrollable question buttons
        int numberOfQuestions = questionMap.size();
        JScrollPane questionScrollPane = new JScrollPane();
        JPanel questionScrollPanel = new JPanel();
        questionScrollPanel.setLayout(new GridLayout(numberOfQuestions, 1));
        for (Integer questionId : questionMap.keySet()) {
            //read all variables from displayFormatter
            QuestionDisplayFormatter question = questionMap.get(questionId);
            String title = question.getTitle();
            String type = question.getType();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineDate = question.getLegalDeadline();
            String deadline = deadlineDate.format(formatter);
            String deadlineLine = "Legal deadline: " + deadline;
            //Format them all into a textArea
            JPanel questionPanel = UIDesign.singleQuestionDrawer(title, type, deadlineLine);

            JButton questionButton = new JButton();
            questionButton.setPreferredSize(new Dimension(400, 80));
            questionButton.add(questionPanel);
            questionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Question selected");
                    SelectQuestionControl selectQuestionControl = controlContainer.getSelectQuestionControl();
                    selectQuestionControl.selectQuestion(questionId, userId);
                }
            });
            questionScrollPanel.add(questionButton);
        }
        questionScrollPane.setViewportView(questionScrollPanel);
        UIDesign.setSizeInLayout(questionScrollPane, new Dimension(360, 600));

        //Home page button
        JButton homePage = new JButton("Home Page");
        UIDesign.setGeneralButton(homePage);
        String stringId = Integer.toString(userId);
        homePage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (stringId.startsWith("1")) {
                    cardLayout.show(screens, "AttorneyHomePageUI");
                } else {
                    cardLayout.show(screens, "ClientHomePageUI");
                }
            }
        });

        //Add everything in the panel
        this.add(homePage);
        this.add(helloMessage);
        this.add(questionScrollPane);

    }
}
