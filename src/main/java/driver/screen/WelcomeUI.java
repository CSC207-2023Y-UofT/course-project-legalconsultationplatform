package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static driver.screen.UIDesign.*;

/**
 * This class represents the initial screen of the application where users can choose to register or log in.
 */
public class WelcomeUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;

    /**
     * Constructs a WelcomeUI instance with the provided control container,
     * card layout, and screens panel.
     *
     * @param controlContainer The container holding various controller instances.
     * @param cardLayout       The CardLayout used for managing screen transitions.
     * @param screens          The panel containing various screens.
     */
    public WelcomeUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.controlContainer = controlContainer;
        UIDesign.setBackgroundFrame(this);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // Create and stylize the title label
        JLabel title = new JLabel("Welcome");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        UIDesign.setTitleFont(title);

        // Add margin around the title
        int topMargin = 50;
        int leftMargin = 0;
        int bottomMargin = 100;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        add(title);

        // Create buttons panel for Register and Login options
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(lightGreenColor);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS));

        // Create Register and Login buttons and apply styling
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        UIDesign.setButton(registerButton);
        UIDesign.setButton(loginButton);

        // Add buttons to the panel with horizontal glue for alignment
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(registerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(loginButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        // Add action listeners to the buttons
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);

        add(buttonsPanel);
    }

    /**
     * Handles user actions performed within the UI, such as button clicks.
     *
     * @param e The ActionEvent representing the user action.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Register".equals(actionCommand)) {
            // Handle the Register button click
            RegisterUI registerUI = new RegisterUI(controlContainer);
            JScrollPane registerScrollPane = new JScrollPane(registerUI);
            screens.add(registerScrollPane, "Register");
            cardLayout.show(screens, "Register");
            System.out.println("User chooses register\nRegister screen showed");
        } else if ("Login".equals(actionCommand)) {
            // Handle the Login button click
            LoginUI loginUI = new LoginUI(controlContainer);
            screens.add(loginUI, "Login");
            cardLayout.show(screens, "Login");
            System.out.println("User chooses login\nLogin screen showed");
        }
    }
}