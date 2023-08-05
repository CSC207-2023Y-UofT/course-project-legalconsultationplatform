package driver.screen;

import adapter.controller.BrowseQuestionControl;
import adapter.controller.ControlContainer;
import adapter.controller.ViewQuestionControl;
import businessrule.usecase.QuestionDisplayFormatter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;
import java.util.Map;

public class AttorneyHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;
    public AttorneyHomePageUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens,
                              int userId, String userName) {
        this.controlContainer = controlContainer;
        this.userId = userId;
        this.userName = userName;
        this.cardLayout = cardLayout;
        this.screens = screens;
        //The title
        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitle(title);

        int topMargin = 50;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        //The userName and userId
        String helloMessageString = "Hello, " + userName + "(" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);

        //The three buttons
        JPanel buttons = new JPanel();
        JButton browseQuestions = new JButton("Browse available questions");
        JButton viewQuestionHistory = new JButton("View question history");
        JButton recommendation = new JButton("Recommended questions");

        UIDesign.setHomePageButton(browseQuestions);
        UIDesign.setHomePageButton(viewQuestionHistory);
        UIDesign.setHomePageButton(recommendation);

        buttons.add(browseQuestions);
        buttons.add(viewQuestionHistory);
        buttons.add(recommendation);
        browseQuestions.addActionListener(this);
        viewQuestionHistory.addActionListener(this);
        recommendation.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(helloMessage);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Browse available questions".equals(actionCommand)){
            System.out.println("Attorney chooses browse available questions.");
            BrowseQuestionControl browseQuestionControl = controlContainer.getBrowseQuestionControl();
            browseQuestionControl.browseQuestion(userId);
        } else if ("View question history".equals(actionCommand)){
            System.out.println("Attorney chooses view question history.");
            ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
            viewQuestionControl.viewQuestion(userId);
        } else if ("Recommended questions".equals(actionCommand)){
            JOptionPane.showMessageDialog(this, "Recommendation unavailable");
        }
    }
}
