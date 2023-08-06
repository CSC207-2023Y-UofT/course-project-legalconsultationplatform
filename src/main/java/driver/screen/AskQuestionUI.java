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

import static driver.screen.UIDesign.buttonSize;
import static javax.swing.BoxLayout.Y_AXIS;


/**
 *
 * @author joseph
 */
public class AskQuestionUI extends JPanel implements ActionListener{
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

        setSize(400, 400);
        setBackground(UIDesign.lightGreenColor);

        //UserName and userId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        JLabel title = new JLabel("New Question");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(UIDesign.lightGreenColor);
        titlePanel.setLayout(new BoxLayout(titlePanel, BoxLayout.Y_AXIS));
        titlePanel.add(title);
        titlePanel.add(helloMessage);

        //Question type and title
        DropDownPanel questionTypeDropDown = new DropDownPanel(new JLabel("Select question type"), questionType);
        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("What is your question title?"), titleForQuestion);
        DateChooserPanel legalDeadlineInfo = new DateChooserPanel(new JLabel("Select your question's legal deadline"), deadlineChooser);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton buttonToSubmit = new JButton("Submit");
        UIDesign.setButton(buttonToSubmit);
        buttons.add(buttonToSubmit);

        buttonToSubmit.addActionListener(this);

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

        this.add(titlePanel);
        this.add(restPanel);

    }
    @Override
    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());
        QuestionControl questionControl = controlContainer.getQuestionControl();
        Date deadlineDate = deadlineChooser.getDate();
        Instant instant = deadlineDate.toInstant();
        ZonedDateTime zonedDateTime = instant.atZone(ZoneId.systemDefault());
        LocalDate deadlinelocalDate = zonedDateTime.toLocalDate();
        try {
            questionControl.createQuestion((String)questionType.getSelectedItem(),
                    titleForQuestion.getText(), LocalDate.now(), userId, deadlinelocalDate);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
