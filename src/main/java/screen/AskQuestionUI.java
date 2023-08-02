package screen;

import askquestion.QuestionControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import static javax.swing.JOptionPane.showMessageDialog;




/**
 *
 * @author joseph
 */
public class AskQuestionUI extends JPanel implements ActionListener{
    QuestionControl control;
    JTextField questionCategory = new JTextField(15);
    // 有问题
    JTextField titleForQuestion = new JTextField(15);
    JTextField clientId = new JTextField(15);
    JTextField legalDeadline = new JTextField(15);
    /**
     * Creates new form AskQuestion
     */
    public AskQuestionUI(QuestionControl control) {

        this.control = control;

        JLabel title = new JLabel("Ask Question Screen");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        LabelTextPanel questionCategoryInfo = new LabelTextPanel(new JLabel("questionCategory"), questionCategory);
        LabelTextPanel clientIdInfo = new LabelTextPanel(new JLabel("clientId"), clientId);
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
            control.createQuestion(questionCategory.getText(), LocalDate.now(), Integer.parseInt(clientId.getText()), LocalDate.parse(legalDeadline.getText()));
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(this, e.getMessage());
        }
    }
}
