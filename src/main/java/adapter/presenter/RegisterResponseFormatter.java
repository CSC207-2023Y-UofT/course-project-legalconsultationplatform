package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import driver.screen.ApplicationException;
import driver.screen.WelcomeUI;

import javax.swing.*;
import java.awt.*;

public class RegisterResponseFormatter implements RegisterOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    public RegisterResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    @Override
    public RegisterResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }
    @Override
    public RegisterResponseModel prepareSuccess(String msg) {
        WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
        screens.add(welcomeUI, "Welcome");
        cardLayout.show(screens, "Welcome");
        JOptionPane.showMessageDialog(null, msg);
        return new RegisterResponseModel();
    }
}
