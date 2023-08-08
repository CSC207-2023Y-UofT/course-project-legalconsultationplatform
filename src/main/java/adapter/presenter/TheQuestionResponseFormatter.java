package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.util.PostDisplayFormatter;
import driver.screen.ApplicationException;
import driver.screen.TheQuestionCloseUI;
import driver.screen.TheQuestionOpenUI;
import driver.screen.TheQuestionTopPanel;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    public TheQuestionResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        int userId = response.getUserId();
        String userName = response.getUserName();
        int questionId = response.getQuestionId();
        String title = response.getTitle();
        String type = response.getType();
        LocalDate deadline = response.getDeadline();
        Map<Integer, PostDisplayFormatter> postMap = response.getPostMap();
        boolean isClose = response.isClose();


        if (isClose) {
            TheQuestionCloseUI closeUI = new TheQuestionCloseUI(controlContainer, cardLayout, screens, userId, userName,
                    questionId, title, type, deadline, postMap);
            screens.add(closeUI, "Close");
            cardLayout.show(screens, "Close");
        } else {
            TheQuestionOpenUI openUI = new TheQuestionOpenUI(controlContainer, cardLayout, screens, userId, userName,
                    questionId, title, type, deadline, postMap);
            screens.add(openUI, "Open");
            cardLayout.show(screens, "Open");
        }
        return response;
    }
}
