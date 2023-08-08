package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.SelectQuestionControl;
import businessrule.usecase.util.QuestionDisplayFormatter;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

public class QuestionListUI extends JPanel implements ActionListener {
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

        JPanel spacer = UIDesign.addSpacer(10);
        JPanel bottomSpacer = UIDesign.addSpacer(20);
        //UserName and UserId
        JPanel helloMessage = UIDesign.helloMessageConstructor(userName, userId);

        //The scrollable question buttons
        JPanel questionScrollPanel = new JPanel();
        UIDesign.setSizeInLayout(questionScrollPanel, new Dimension(350, 450));
        questionScrollPanel.setBackground(UIDesign.lightGreenColor);
        questionScrollPanel.setLayout(new BoxLayout(questionScrollPanel, BoxLayout.Y_AXIS));
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
            UIDesign.setSizeInLayout(questionButton, new Dimension(340, 60));
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

        JScrollPane questionScrollPane = new JScrollPane();
        questionScrollPane.setViewportView(questionScrollPanel);
        UIDesign.setSizeInLayout(questionScrollPane, new Dimension(350, 450));
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Home page button
        JButton homePage = new JButton("Home Page");
        UIDesign.setGeneralButton(homePage);
        homePage.setAlignmentX(CENTER_ALIGNMENT);
        homePage.addActionListener(this);

        //Add everything in the panel
        setBackground(UIDesign.lightGreenColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        add(spacer);
        add(helloMessage);
        add(questionScrollPane);
        add(bottomSpacer);
        add(homePage);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String stringId = Integer.toString(userId);
        if (stringId.startsWith("2")) {
            ClientHomePageUI clientUI = new ClientHomePageUI(controlContainer, cardLayout,
                    screens, userId, userName);
            screens.add(clientUI, "ClientHomePage");
            cardLayout.show(screens, "ClientHomePage");
        } else {
            AttorneyHomePageUI attorneyUI = new AttorneyHomePageUI(controlContainer, cardLayout,
                    screens, userId, userName);
            screens.add(attorneyUI, "AttorneyHomePage");
            cardLayout.show(screens, "AttorneyHomePage");
        }
    }
}

