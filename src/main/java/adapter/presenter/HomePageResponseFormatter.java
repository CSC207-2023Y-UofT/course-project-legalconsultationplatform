package adapter.presenter;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.responsemodel.HomePageResponseModel;
import driver.screen.ApplicationException;
import driver.screen.AttorneyHomePageUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomePageResponseFormatter implements HomePageOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;

    public HomePageResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public HomePageResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public HomePageResponseModel prepareSuccess(HomePageResponseModel response) {
        AttorneyHomePageUI AttorneyHomePage = new AttorneyHomePageUI();
        screens.add(AttorneyHomePage, "AttorneyHomePage");
        cardLayout.show(screens, "AttorneyHomePage");
        return response;
    }
}
