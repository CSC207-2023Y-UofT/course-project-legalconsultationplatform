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

/**
 * This class represents a JPanel for a list of questions displayed in the user interface.
 */
public class QuestionListUI extends JPanel {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    Map<Integer, QuestionDisplayFormatter> questionMap;

    /**
     * Constructs a new QuestionListUI instance.
     *
     * @param controlContainer The container for controllers.
     * @param cardLayout The CardLayout for managing screens.
     * @param screens The parent panel containing multiple screens.
     * @param userId The ID of the logged-in user.
     * @param userName The name of the logged-in user.
     * @param questionMap A map of question IDs to their display formatters.
     */
    public QuestionListUI(ControlContainer controlContainer, CardLayout cardLayout,
                          JPanel screens, int userId, String userName,
                          Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;
        this.questionMap = questionMap;

        // User greeting message
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        // Scrollable question buttons
        int numberOfQuestions = questionMap.size();
        JScrollPane questionScrollPane = new JScrollPane();
        JPanel questionScrollPanel = new JPanel();
        questionScrollPanel.setLayout(new GridLayout(numberOfQuestions, 1));

        for (Integer questionId : questionMap.keySet()) {
            QuestionDisplayFormatter question = questionMap.get(questionId);
            String title = question.getTitle();
            String type = question.getType();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate deadlineDate = question.getLegalDeadline();
            String deadline = deadlineDate.format(formatter);

            String overallText = "<html><b>" + title + "</b><br>" + "Legal deadline: " + deadline + "</html>";

            JButton questionButton = new JButton(overallText);
            questionButton.setPreferredSize(new Dimension(400, 50));
            questionScrollPanel.add(questionButton);

            questionButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    System.out.println("Question selected");
                    SelectQuestionControl selectQuestionControl = controlContainer.getSelectQuestionControl();
                    selectQuestionControl.selectQuestion(questionId, userId);
                }
            });
        }

        questionScrollPane.setViewportView(questionScrollPanel);

        // Add components to the panel
        this.add(helloMessage);
        this.add(questionScrollPane);
    }
}
