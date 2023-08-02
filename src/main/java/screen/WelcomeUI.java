package screen;

import presenter.ClientRegisterControl;
import presenter.UserLoginControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeUI extends JPanel implements ActionListener{
    CardLayout cardLayout;
    JPanel screens;
    UserLoginControl loginControl;
    ClientRegisterControl registerControl;
    public WelcomeUI(CardLayout cardLayout, JPanel screens, UserLoginControl loginControl,
                     ClientRegisterControl registerControl){
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.loginControl = loginControl;
        this.registerControl = registerControl;
        JLabel title = new JLabel("Welcome!");
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JPanel buttons = new JPanel();
        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");

        buttons.add(registerButton);
        buttons.add(loginButton);

        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(title);
        this.add(buttons);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionCommand = e.getActionCommand();
        if ("Register".equals(actionCommand)){
            RegisterUI registerUI = new RegisterUI(registerControl);
            screens.add(registerUI, "Register");
            cardLayout.show(screens, "Register");
        } else if ("Login".equals(actionCommand)){
            LoginUI loginUI = new LoginUI(loginControl);
            screens.add(loginUI, "Login");
            cardLayout.show(screens, "Login");
        }
    }
}
