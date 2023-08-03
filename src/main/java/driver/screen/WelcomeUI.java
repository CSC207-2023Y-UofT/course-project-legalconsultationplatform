package driver.screen;

import adapter.controller.UserLoginControl;
import adapter.controller.ClientRegisterControl;
import adapter.presenter.HomePageResponseFormatter;
import adapter.presenter.RegisterResponseFormatter;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.ClientRegisterInputBoundary;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.usecase.ClientRegisterInteractor;
import businessrule.usecase.UserLoginInteractor;
import driver.database.ClientRepository;
import entity.ClientFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WelcomeUI extends JPanel implements ActionListener{
    CardLayout cardLayout;
    JPanel screens;
    public WelcomeUI(CardLayout cardLayout, JPanel screens){
        this.cardLayout = cardLayout;
        this.screens = screens;
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
            ClientGateway clientGateway = new ClientRepository();
            ClientFactory clientFactory = new ClientFactory();
            RegisterOutputBoundary registerOutputBoundary = new RegisterResponseFormatter(cardLayout, screens);
            ClientRegisterInputBoundary clientRegisterInteractor = new ClientRegisterInteractor(clientGateway, registerOutputBoundary,
                    clientFactory);
            ClientRegisterControl registerControl = new ClientRegisterControl(clientRegisterInteractor);

            RegisterUI registerUI = new RegisterUI(registerControl);
            screens.add(registerUI, "Register");
            cardLayout.show(screens, "Register");
        } else if ("Login".equals(actionCommand)){
            UserGatewayFactory gatewayFactory = new UserGatewayFactory();
            HomePageOutputBoundary homePageOutputBoundary = new HomePageResponseFormatter(cardLayout, screens);
            UserLoginInputBoundary userLoginInteractor = new UserLoginInteractor(gatewayFactory, homePageOutputBoundary);
            UserLoginControl loginControl = new UserLoginControl(userLoginInteractor);

            LoginUI loginUI = new LoginUI(loginControl);
            screens.add(loginUI, "Login");
            cardLayout.show(screens, "Login");
        }
    }
}
