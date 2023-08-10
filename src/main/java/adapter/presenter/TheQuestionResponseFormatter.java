package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.util.PostDisplayFormatter;
import entity.ApplicationException;
import driver.screen.TheQuestionUI;

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

        TheQuestionUI questionUI = new TheQuestionUI(controlContainer, cardLayout, screens, userId, userName,
                questionId,  title, type, deadline, postMap);
        screens.add(questionUI, "question");
        cardLayout.show(screens, "question");
        return response;
    }
}
