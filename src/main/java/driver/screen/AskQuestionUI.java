package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.QuestionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import javax.swing.JPanel;

import com.toedter.calendar.JDateChooser;



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

        //UserName and userId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        JLabel title = new JLabel("Ask a new question here");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Question type and title
        DropDownPanel questionTypeDropDown = new DropDownPanel(new JLabel("Select question"), questionType);
        LabelTextPanel titleInfo = new LabelTextPanel(new JLabel("title"), titleForQuestion);
        LabelTextPanel legalDeadlineInfo = new LabelTextPanel(new JLabel("legalDeadline"), legalDeadline);
        JButton buttonToSubmit = new JButton("Submit");

        JPanel buttons = new JPanel();
        buttons.add(buttonToSubmit);

        buttonToSubmit.addActionListener(this);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(title);
        this.add(questionCategoryInfo);
        this.add(clientIdInfo);
        this.add(titleInfo);
        this.add(legalDeadlineInfo);
        this.add(buttons);

    }
    @Override
    public void actionPerformed(ActionEvent evt){
        System.out.println("Click" + evt.getActionCommand());

        try {
            control.createQuestion(questionCategory.getText(), titleForQuestion.getText(), LocalDate.now(), Integer.parseInt(clientId.getText()), LocalDate.parse(legalDeadline.getText()));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
