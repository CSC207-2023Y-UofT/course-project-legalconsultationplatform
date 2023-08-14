package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.QuestionControl;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import static driver.screen.UIDesign.*;
import static driver.screen.UIDrawer.*;


/**
 *
 * @author joseph
 */
public class AskQuestionUI extends UserUI implements ActionListener {
    JComboBox<String> questionType = new JComboBox<>(QUESTION_TYPE_LIST);
    JTextField titleForQuestion = new JTextField(15);
    JDateChooser deadlineChooser = new JDateChooser();
    private static final String TITLE = "New Question";
    private static final String[] QUESTION_TYPE_LIST = {
            "Family and Children", "Individual Rights",
            "Consumer Financial Questions", "Housing and Homelessness",
            "Income Maintenance", "Health and Disability",
            "Work, Employment and Unemployment", "Juvenile", "Education", "Other"
    };
    private static final String QUESTION_TYPE_PROMPT = "Select your question type";
    private static final String QUESTION_TITLE_PROMPT = "Summarize your question in one sentence";
    private static final String DEADLINE_PROMPT = "What is your question's legal deadline?";
    private static final String SUBMIT_BUTTON_NAME = "Submit";
    private static final String CANCEL_BUTTON_NAME = "Cancel";

    /**
     * Creates new form AskQuestion
     */
    public AskQuestionUI(String userName, int userId, UIManager UIManager) {
        super(userName, userId, UIManager);


        //Spacer
        JPanel spacer = addSpacer(30);
        JPanel spacer2 = addSpacer(20);

        //Title
        JLabel title = new JLabel(TITLE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);

        //Question type and title

        JPanel inputPanel = new JPanel();
        setSizeInLayout(inputPanel, new Dimension(360, 150));
        JPanel questionTypePanel = dropDownPanelDrawer(new JLabel(QUESTION_TYPE_PROMPT), questionType);
        JPanel questionTitlePanel = labelTextPanelDrawer(new JLabel(QUESTION_TITLE_PROMPT), titleForQuestion);
        JPanel legalDeadlinePanel = datePanelDrawer(new JLabel(DEADLINE_PROMPT), deadlineChooser);
        inputPanel.add(questionTypePanel);
        inputPanel.add(spacer2);
        inputPanel.add(questionTitlePanel);
        inputPanel.add(spacer2);
        inputPanel.add(legalDeadlinePanel);
        inputPanel.add(spacer2);

        //Buttons
        List<String> buttonList = new ArrayList<>();
        buttonList.add(SUBMIT_BUTTON_NAME);
        buttonList.add(CANCEL_BUTTON_NAME);
        JPanel buttons = setButtonPanel(buttonList, new Dimension(150, 50), 20, this);

        add(helloMessage);
        add(spacer);
        add(title);
        add(spacer2);
        add(inputPanel);
        add(spacer);
        add(buttons);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        ControlContainer controlContainer = uiManager.getControlContainer();
        JPanel screens = uiManager.getScreens();
        CardLayout cardLayout = uiManager.getCardLayout();
        switch (actionCommand) {
            case SUBMIT_BUTTON_NAME:
                System.out.println("Click" + evt.getActionCommand());
                QuestionControl questionControl = controlContainer.getQuestionControl();
                Date deadlineDate = deadlineChooser.getDate();
                LocalDate deadlinelocalDate = null;
                if (deadlineDate != null) {
                    Instant instant = deadlineDate.toInstant();
                    ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
                    deadlinelocalDate = zonedDateTime.toLocalDate();
                }
                try {
                    System.out.println("it's been in questioncontrol");
                    questionControl.createQuestion((String) questionType.getSelectedItem(),
                            titleForQuestion.getText(), LocalDate.now(), deadlinelocalDate);
                } catch (Exception e) {
                    e.printStackTrace();
                    JOptionPane.showMessageDialog(this, e.getMessage());
                }
                break;

            case CANCEL_BUTTON_NAME:
                cardLayout.show(screens, "Home Page");
                break;
        }
    }
}
