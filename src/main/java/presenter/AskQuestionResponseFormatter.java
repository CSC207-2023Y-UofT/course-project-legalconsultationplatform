package presenter;
import screen.ClientHomePageUI;
import screen.FailMessage;

import javax.swing.*;
import java.awt.*;

public class AskQuestionResponseFormatter implements TheQuestionOutputBoundary{
    CardLayout cardLayout;
    JPanel screens;

    public AskQuestionResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        throw new FailMessage(msg);
    }

    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        ClientHomePageUI clientHomePageUI = new ClientHomePageUI();
        screens.add(clientHomePageUI, "ClientHomePage");
        cardLayout.show(screens, "ClientHomePage");
        return response;
    }
}
