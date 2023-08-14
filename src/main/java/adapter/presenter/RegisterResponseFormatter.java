package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.UIFactory;
import businessrule.outputboundary.BaseOutputBoundary;
import businessrule.responsemodel.BaseResponseModel;
import driver.screen.UIManager;
import entity.ApplicationException;
//import driver.screen.WelcomeUI;
import javax.swing.*;
import java.awt.*;

public class RegisterResponseFormatter implements BaseOutputBoundary {
    UIManager UIManager;

    public RegisterResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    @Override
    public BaseResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }
    @Override
    public BaseResponseModel prepareSuccess(String msg) {
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel welcomeUI = UIFactory.getUI(UIFactory.UIType.WELCOME_UI, UIManager, new BaseResponseModel());
        screens.add(welcomeUI, "Welcome");
        cardLayout.show(screens, "Welcome");
        JOptionPane.showMessageDialog(null, msg);
        return new BaseResponseModel();
    }
}
