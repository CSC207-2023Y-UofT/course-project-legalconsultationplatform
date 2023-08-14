package adapter.presenter;
import businessrule.UIFactory;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
import driver.screen.HomePageUI;
import driver.screen.UIManager;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;

public class HomePageResponseFormatter implements UserOutputBoundary {
    UIManager UIManager;

    public HomePageResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    @Override
    public UserResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public UserResponseModel prepareSuccess(UserResponseModel response) {
        pageConstructor(response);
        return response;
    }

    protected void pageConstructor(UserResponseModel response){
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel homePage = UIFactory.getUI(UIFactory.UIType.HOME_PAGE, UIManager, response);
        screens.add(homePage, "Home Page");
        cardLayout.show(screens, "Home Page");
    }
}
