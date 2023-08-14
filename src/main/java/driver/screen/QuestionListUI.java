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

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;

public class QuestionListUI extends UserUI implements ActionListener {
    Map<Integer, QuestionDisplayFormatter> questionMap;
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";

    public QuestionListUI(String userName, int userId, UIManager UIManager,
                          Map<Integer, QuestionDisplayFormatter> questionMap) {
        super(userName, userId, UIManager);
        this.questionMap = questionMap;

        //spacer

        JPanel spacer = addSpacer(10);

        //The scrollable question buttons
        JPanel questionScrollPanel = new JPanel();
        UIDesign.setSizeInLayout(questionScrollPanel, new Dimension(350, 450));
        questionScrollPanel.setBackground(lightGreenColor);
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
            JPanel questionPanel = singleQuestionDrawer(title, type, deadlineLine);

            JButton questionButton = new JButton();
            UIDesign.setSizeInLayout(questionButton, new Dimension(340, 60));
            questionButton.add(questionPanel);
            questionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Question selected");
                    ControlContainer controlContainer = UIManager.getControlContainer();
                    SelectQuestionControl selectQuestionControl = controlContainer.getSelectQuestionControl();
                    selectQuestionControl.selectQuestion(questionId);
                }
            });
            questionScrollPanel.add(questionButton);
        }

        JScrollPane questionScrollPane = new JScrollPane();
        questionScrollPane.setBackground(lightGreenColor);
        questionScrollPane.setViewportView(questionScrollPanel);
        UIDesign.setSizeInLayout(questionScrollPane, new Dimension(350, 450));
        questionScrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        questionScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        //Home page button
        JButton homePage = new JButton(HOME_PAGE_BUTTON_NAME);
        setButton(homePage, new Dimension(150, 50));
        homePage.setAlignmentX(CENTER_ALIGNMENT);
        homePage.addActionListener(this);

        //Add everything in the panel
        add(spacer);
        add(helloMessage);
        add(spacer);
        add(questionScrollPane);
        add(spacer);
        add(homePage);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        JPanel screens = uiManager.getScreens();
        CardLayout cardLayout = uiManager.getCardLayout();
        cardLayout.show(screens, "Home Page");
    }
}

