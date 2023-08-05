package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import static driver.screen.UIDesign.buttonSize;

public class WelcomeUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;

    public WelcomeUI(ControlContainer controlContainer, CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.controlContainer = controlContainer;
        setSize(400, 400);
        setBackground(UIDesign.backgroundColor);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Welcome");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);
        title.setFont(UIDesign.titleFont);

        int topMargin = 30;
        int leftMargin = 0;
        int bottomMargin = 100;
        int rightMargin = 0;
        title.setBorder(new EmptyBorder(topMargin, leftMargin, bottomMargin, rightMargin));

        add(title);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setBackground(UIDesign.backgroundColor);
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS)); // Use X_AXIS for horizontal alignment

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        registerButton.setPreferredSize(UIDesign.buttonSize);
        loginButton.setPreferredSize(UIDesign.buttonSize);

        buttonsPanel.add(Box.createHorizontalGlue()); // Add glue to push buttons to the left
        buttonsPanel.add(registerButton);
        buttonsPanel.add(Box.createHorizontalGlue()); // Add glue to push buttons to the center
        buttonsPanel.add(loginButton);
        buttonsPanel.add(Box.createHorizontalGlue()); // Add glue to push buttons to the right

        registerButton.setFont(UIDesign.boldFont);
        loginButton.setFont(UIDesign.boldFont);
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);

        add(buttonsPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Register".equals(actionCommand)){
            RegisterUI registerUI = new RegisterUI(controlContainer);
            JScrollPane registerScrollPane = new JScrollPane(registerUI);
            screens.add(registerScrollPane, "Register");
            cardLayout.show(screens, "Register");
            System.out.println("User chooses register\nRegister screen showed");
        } else if ("Login".equals(actionCommand)){
            LoginUI loginUI = new LoginUI(controlContainer);
            screens.add(loginUI, "Login");
            cardLayout.show(screens, "Login");
            System.out.println("User chooses login\nLogin screen showed");
        }
    }
}
