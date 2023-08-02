package presenter;
import screen.AttorneyHomePageUI;
import screen.FailMessage;
import userlogin.LoginResponseModel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class LoginResponseFormatter implements LoginOutputBoundary{
    CardLayout cardLayout;
    JPanel screens;

    public LoginResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public LoginResponseModel prepareFail(String msg) {
        throw new FailMessage(msg);
    }

    @Override
    public LoginResponseModel prepareSuccess(LoginResponseModel response) {
        LocalDateTime responseTime = LocalDateTime.parse(response.getCreationTime());
        response.setCreationTime(responseTime.format(DateTimeFormatter.ofPattern("hh:mm:ss")));
        AttorneyHomePageUI AttorneyHomePage = new AttorneyHomePageUI();
        screens.add(AttorneyHomePage, "AttorneyHomePage");
        cardLayout.show(screens, "AttorneyHomePage");
        return response;
    }
}
