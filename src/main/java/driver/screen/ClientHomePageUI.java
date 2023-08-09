package driver.screen;

import adapter.controller.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * This class represents a JPanel for the Client's home page user interface.
 */
public class ClientHomePageUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;
    int userId;
    String userName;

    /**
     * Constructs a new ClientHomePageUI instance.
     *
     * @param controlContainer The container for controllers.
     * @param cardLayout The CardLayout for managing screens.
     * @param screens The parent panel containing multiple screens.
     * @param userId The ID of the logged-in client user.
     * @param userName The name of the logged-in client user.
     */
    public ClientHomePageUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens,
                            int userId, String userName) {
        this.controlContainer = controlContainer;
        this.userId = userId;
        this.userName = userName;
        this.cardLayout = cardLayout;
        this.screens = screens;

        // UI Design setup
        setBackground(UIDesign.lightGreenColor);
        JLabel title = new JLabel("Home");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);

        // Title top margin
        int topMargin = 50;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        // User greeting message
        String helloMessageString = "Hello, " + userName + " (" + userId + ")";
        JLabel helloMessage = new JLabel(helloMessageString);
        helloMessage.setBorder(new EmptyBorder(0, 0, 50, 0));

        // Buttons panel
        JPanel buttons = new JPanel();
        buttons.setBackground(UIDesign.lightGreenColor);
        JButton askNewQuestion = new JButton("Ask new question");
        JButton viewQuestionHistory = new JButton("View question history");

        UIDesign.setHomePageButton(askNewQuestion);
        UIDesign.setHomePageButton(viewQuestionHistory);
        buttons.add(askNewQuestion);
        buttons.add(viewQuestionHistory);
        askNewQuestion.addActionListener(this);
        viewQuestionHistory.addActionListener(this);

        this.add(helloMessage);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Ask new question".equals(actionCommand)){
            System.out.println("Client chooses to ask new question.");
            AskQuestionUI askQuestionUI = new AskQuestionUI(controlContainer, cardLayout, screens, userId, userName);
            screens.add(askQuestionUI, "askQuestion");
            cardLayout.show(screens, "askQuestion");
        } else if ("View question history".equals(actionCommand)){
            System.out.println("Client chooses to view question history.");
            ViewQuestionControl viewQuestionControl = controlContainer.getViewQuestionControl();
            viewQuestionControl.viewQuestion(userId);
        }
    }
}
