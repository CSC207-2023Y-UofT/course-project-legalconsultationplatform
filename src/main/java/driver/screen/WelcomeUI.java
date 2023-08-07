package driver.screen;

import adapter.controller.ControlContainer;
import com.toedter.components.JTitlePanel;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.rmi.server.UID;

import static driver.screen.UIDesign.*;

public class WelcomeUI extends JPanel implements ActionListener {
    ControlContainer controlContainer;
    CardLayout cardLayout;
    JPanel screens;

    public WelcomeUI (ControlContainer controlContainer, CardLayout cardLayout, JPanel screens){
        this.cardLayout = cardLayout;
        this.screens = screens;
        this.controlContainer = controlContainer;

        //overall layout
        UIDesign.setBackgroundFrame(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        //Top spacer
        JPanel topSpacer = addSpacer(160);

        //Welcome title
        Dimension titleDimension = new Dimension(360, 80);
        JLabel title = new JLabel("Welcome");
        setSizeInLayout(title, titleDimension);
        title.setOpaque(false);
        setTitleFont(title);

        //Middle spacer
        JPanel middleSpacer = addSpacer(80);

        //Buttons panel

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setSize(new Dimension(360, 240));
        buttonsPanel.setLayout(new BoxLayout(buttonsPanel, BoxLayout.X_AXIS)); // Use X_AXIS for horizontal alignment
        buttonsPanel.setOpaque(false);

        JButton registerButton = new JButton("Register");
        JButton loginButton = new JButton("Login");
        registerButton.addActionListener(this);
        loginButton.addActionListener(this);
        setGeneralButton(registerButton);
        setGeneralButton(loginButton);

        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(registerButton);
        buttonsPanel.add(Box.createHorizontalGlue());
        buttonsPanel.add(loginButton);
        buttonsPanel.add(Box.createHorizontalGlue());

        add(topSpacer);
        add(title);
        add(middleSpacer);
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
