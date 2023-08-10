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
import java.util.Date;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;

import static javax.swing.BoxLayout.Y_AXIS;


/**
 *
 * @author joseph
 */
public class AskQuestionUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    String[] questionTypeList = {"Family and Children", "Individual Rights",
            "Consumer Financial Questions", "Housing and Homelessness",
            "Income Maintenance", "Health and Disability",
            "Work, Employment and Unemployment", "Juvenile", "Education", "Other"};
    JComboBox<String> questionType = new JComboBox<>(questionTypeList);
    JTextField titleForQuestion = new JTextField(15);
    JDateChooser deadlineChooser = new JDateChooser();

    /**
     * Creates new form AskQuestion
     */
    public AskQuestionUI(ControlContainer controlContainer, CardLayout cardLayout,
                         JPanel screens, int userId, String userName) {

        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.userId = userId;
        this.userName = userName;

        setSize(360, 600);
        setBackground(UIDesign.lightGreenColor);

        //UserName and userId
        JPanel helloMessage = UIDesign.helloMessageConstructor(userName, userId);

        JPanel spacer = UIDesign.addSpacer(50);
        JPanel spacer2 = UIDesign.addSpacer(50);

        //Title
        JLabel title = new JLabel("New Question");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);

        //Question type and title
        DropDownPanel questionTypeDropDown = new DropDownPanel(new JLabel("Select question type"), questionType);
        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("What is your question title?"), titleForQuestion);
        DateChooserPanel legalDeadlineInfo = new DateChooserPanel(new JLabel("Select your question's legal deadline"), deadlineChooser);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton buttonToSubmit = new JButton("Submit");
        JButton returnButton = new JButton("Home Page");
        UIDesign.setButton(buttonToSubmit);
        UIDesign.setButton(returnButton);
        buttons.add(buttonToSubmit);
        buttons.add(returnButton);

        buttonToSubmit.addActionListener(this);
        returnButton.addActionListener(this);

        JPanel restPanel = new JPanel();
        restPanel.setBackground(UIDesign.lightGreenColor);
        restPanel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component

        restPanel.add(questionTypeDropDown, gbc);
        restPanel.add(titleInfo, gbc);
        restPanel.add(legalDeadlineInfo, gbc);
        restPanel.add(buttons, gbc);

        this.add(helloMessage);
        this.add(spacer);
        this.add(title);
        this.add(spacer2);
        this.add(restPanel);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        if ("Submit".equals(actionCommand)) {
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
                questionControl.createQuestion((String) questionType.getSelectedItem(),
                        titleForQuestion.getText(), LocalDate.now(), userId, deadlinelocalDate);
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if ("Home Page".equals(actionCommand)) {
            ClientHomePageUI homePageUI = new ClientHomePageUI(controlContainer, cardLayout, screens,
                    userId, userName);
            screens.add(homePageUI, "HomePage");
            cardLayout.show(screens, "HomePage");
        }
    }
}
