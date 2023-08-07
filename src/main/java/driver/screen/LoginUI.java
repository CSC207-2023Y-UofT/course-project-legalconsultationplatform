package driver.screen;

import adapter.controller.ControlContainer;
import adapter.controller.UserLoginControl;
import com.objectdb.o.JPA;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.server.UID;


/**
 *
 * @author kaxi
 */
public class LoginUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;

    JPanel screens;
    JTextField userId = new JTextField(15);
    JPasswordField password = new JPasswordField(15);

    /**
     * Creates new form UserLogin
     */
    public LoginUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens) {

        this.controlContainer = controlContainer;
        this.cardLayout = cardLayout;
        this.screens = screens;
        UIDesign.setBackgroundFrame(this);
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around each component

        // Create the title label
        JLabel title = new JLabel("Log In");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);
        int topMargin = 30;
        int leftMargin = 0;
        int bottomMargin = 0;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));
        add(title, gbc);

        // Add some vertical glue between the title, userId, and password

        gbc.gridx = 0; // Set the gridx to 0 to place components under the title

        LabelTextPanel userIdPanel = new LabelTextPanel(new JLabel("User Id"), userId);
        add(userIdPanel, gbc);

        LabelTextPanel passwordPanel = new LabelTextPanel(new JLabel("Password"), password);
        add(passwordPanel, gbc);

        JButton buttonToSubmit = new JButton("Login");
        JButton homepageButton = new JButton("Welcome Page");
        UIDesign.setButton(buttonToSubmit);
        UIDesign.setButton(homepageButton);
        int spacing = 10;
        JPanel buttons = new JPanel();
        buttons.setLayout(new BoxLayout(buttons, BoxLayout.Y_AXIS));
        buttons.setBackground(UIDesign.lightGreenColor);
        buttons.add(buttonToSubmit);
        buttons.add(Box.createVerticalStrut(10));
        buttons.add(homepageButton);
        buttons.setAlignmentX(LEFT_ALIGNMENT);
        buttonToSubmit.addActionListener(this);
        homepageButton.addActionListener(this);
        add(buttons, gbc);

    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        String actionCommand = evt.getActionCommand();
        UserLoginControl loginControl = controlContainer.getUserLoginControl();
        if ("Login".equals(actionCommand)) {
            try {
                loginControl.login(Integer.parseInt(userId.getText()), String.valueOf(password.getPassword()));
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, e.getMessage());
            }
        } else if ("Welcome Page".equals(actionCommand)) {
            WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
            screens.add(welcomeUI, "Welcome");
            cardLayout.show(screens, "Welcome");
        }
    }
}