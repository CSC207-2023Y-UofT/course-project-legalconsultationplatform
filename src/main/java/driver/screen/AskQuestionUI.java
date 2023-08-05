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
        setBackground(UIDesign.backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //UserName and userId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        JLabel title = new JLabel("Ask a new question here");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setForeground(UIDesign.titleColor);
        title.setFont(UIDesign.subTitleFont);

        //Question type and title
        DropDownPanel questionTypeDropDown = new DropDownPanel(new JLabel("Select question type"), questionType);
        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("title"), titleForQuestion);
        titleForQuestion.setMaximumSize(new Dimension(300, 25000));
        titleInfo.setAlignmentX(Component.CENTER_ALIGNMENT);
        DateChooserPanel legalDeadlineInfo = new DateChooserPanel(new JLabel("legalDeadline"), deadlineChooser);
        JButton buttonToSubmit = new JButton("Submit");
        buttonToSubmit.setMinimumSize(buttonSize);
        buttonToSubmit.setPreferredSize(buttonSize);
        buttonToSubmit.setMaximumSize(buttonSize);

        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.backgroundColor);
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.X_AXIS)); // Use X_AXIS for horizontal alignment
        buttons.add(buttonToSubmit);

        buttonToSubmit.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(helloMessage);
        this.add(questionTypeDropDown);
        this.add(titleInfo);
        this.add(legalDeadlineInfo);
        this.add(buttons);

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
