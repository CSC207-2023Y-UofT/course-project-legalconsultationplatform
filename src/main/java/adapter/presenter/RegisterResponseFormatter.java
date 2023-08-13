package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.responsemodel.BaseResponseModel;
import entity.ApplicationException;
//import driver.screen.WelcomeUI;
import javax.swing.*;
import java.awt.*;

public class RegisterResponseFormatter implements BaseOutputBoundary {
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
    public BaseResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }
    @Override
    public BaseResponseModel prepareSuccess(String msg) {
//        WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
//        screens.add(welcomeUI, "Welcome");
        cardLayout.show(screens, "Welcome");
        JOptionPane.showMessageDialog(null, msg);
        return new BaseResponseModel();
    }
}
