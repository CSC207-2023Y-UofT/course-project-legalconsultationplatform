package driver.screen;

import adapter.controller.ControlContainer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeUI extends JPanel implements ActionListener{
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;
    public WelcomeUI(CardLayout cardLayout, JPanel screens, ControlContainer controlContainer){
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.controlContainer = controlContainer;
        JLabel title = new JLabel("Welcome!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        buttons.add(registerButton);
        buttons.add(loginButton);
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Register".equals(actionCommand)){
            RegisterUI registerUI = new RegisterUI(controlContainer);
            screens.add(registerUI, "Register");
            cardLayout.show(screens, "Register");
            System.out.println("Register screen showed");
        } else if ("Login".equals(actionCommand)){
            LoginUI loginUI = new LoginUI(controlContainer);
            screens.add(loginUI, "Login");
            cardLayout.show(screens, "Login");
            System.out.println("Login screen showed");
        }
    }
}
