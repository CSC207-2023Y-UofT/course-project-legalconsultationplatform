package presenter;
import screen.AttorneyHomePageUI;
import screen.ClientHomePageUI;
import screen.UserCreationFailed;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class AskQuestionResponseFormatter implements TheQuestionOutputBoundary{
    CardLayout cardLayout;
    JPanel screens;

    public AskQuestionResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        throw new UserCreationFailed(msg);
    }

    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        ClientHomePageUI clientHomePageUI = new ClientHomePageUI();
        screens.add(clientHomePageUI, "ClientHomePage");
        cardLayout.show(screens, "ClientHomePage");
        return response;
    }
}
