package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.ViewQuestionControl;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a JPanel for the Attorney's home page user interface.
 */
public class AttorneyHomePageUI extends JPanel implements ActionListener {
    private ControlContainer controlContainer;
    private CardLayout cardLayout;
    private JPanel screens;
    private int userId;
    private String userName;

    /**
     * Constructs an AttorneyHomePageUI instance.
     *
     * @param controlContainer The container for controllers.
     * @param cardLayout The CardLayout for managing screens.
     * @param screens The parent panel containing multiple screens.
     * @param userId The ID of the logged-in attorney user.
     * @param userName The name of the logged-in attorney user.
     */
    public AttorneyHomePageUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens,
                              int userId, String userName) {
        this.controlContainer = controlContainer;
        this.userId = userId;
        this.userName = userName;
        this.cardLayout = cardLayout;
        this.screens = screens;

        // UI Design setup
        UIDesign.setBackgroundFrame(this);

        // The title
        JLabel title = new JLabel("Home Page");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);
        title.setBorder(new EmptyBorder(50, 0, 0, 0));

        // User greeting message
        String helloMessageString = "Hello, " + userName + " (" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        helloMessage.setBorder(new EmptyBorder(0, 0, 30, 0));

        // Buttons panel
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton browseQuestions = new JButton("Browse available questions");
        browseQuestions.setBorder(new EmptyBorder(0, 0, 20, 0));
        JButton viewQuestionHistory = new JButton("View question history");
        viewQuestionHistory.setBorder(new EmptyBorder(0, 0, 20, 0));
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
            // Uncomment when functionality is implemented
            // BrowseQuestionControl browseQuestionControl = controlContainer.getBrowseQuestionControl();
            // browseQuestionControl.browseQuestion(userId);
        } else if ("View question history".equals(actionCommand)){
            System.out.println("Attorney chooses view question history.");
            ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
            viewQuestionControl.viewQuestion(userId);
        } else if ("Recommended questions".equals(actionCommand)){
            JOptionPane.showMessageDialog(this, "Recommendation unavailable");
        }
    }
}