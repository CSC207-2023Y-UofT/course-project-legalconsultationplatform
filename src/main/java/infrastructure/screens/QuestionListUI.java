package infrastructure.screens;
import adapters.controllers.ControlContainer;
import adapters.controllers.SelectQuestionControl;
import infrastructure.screens.utils.UIDesign;
import infrastructure.screens.utils.UIManager;
import usecases.dto.QuestionDisplay;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;
import static infrastructure.screens.utils.UIDesign.*;
import static infrastructure.screens.utils.UIDrawer.*;

/**
 * This class represents a JPanel for a list of questions displayed in the user interface.
 */
public class QuestionListUI extends UserUI implements ActionListener {
    Map<Integer, QuestionDisplay> questionMap;
    static final String HOME_PAGE_BUTTON_NAME = "Home Page";

    /**
     * Constructs a new QuestionListUI instance.
     *
     * @param userId The ID of the logged-in user.
     * @param userName The name of the logged-in user.
     * @param UIManager A UIManager class that contains screen, cardlayout, and control container
     * @param questionMap A map of question IDs to their display formatters.
     */
    public QuestionListUI(String userName, int userId, UIManager UIManager,
                          Map<Integer, QuestionDisplay> questionMap) {
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
            QuestionDisplay question = questionMap.get(questionId);
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
            questionButton.addActionListener(e -> {
                System.out.println("Question selected");
                ControlContainer controlContainer = UIManager.getControlContainer();
                SelectQuestionControl selectQuestionControl = controlContainer.getSelectQuestionControl();
                selectQuestionControl.selectQuestion(questionId);
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

