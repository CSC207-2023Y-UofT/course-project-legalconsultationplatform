package adapter.presenter;
import adapter.controller.ControlContainer;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;

public class HomePageResponseFormatter implements UserOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    public HomePageResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    @Override
    public UserResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public UserResponseModel prepareSuccess(UserResponseModel response) {
        int userId = response.getUserId();
        String userName = response.getUserName();
        String userType = response.getUserType();
//        if (userType.equals("Attorney")) {
//            AttorneyHomePageUI AttorneyHomePage = new AttorneyHomePageUI(controlContainer, cardLayout, screens,
//                    userId, userName);
//            screens.add(AttorneyHomePage, "AttorneyHomePage");
//            cardLayout.show(screens, "AttorneyHomePage");
//        } else {
//            ClientHomePageUI clientHomePage = new ClientHomePageUI(controlContainer, cardLayout, screens,
//                    userId, userName);
//            screens.add(clientHomePage, "ClientHomePage");
//            cardLayout.show(screens, "ClientHomePage");
//        }
        return response;
    }
}
