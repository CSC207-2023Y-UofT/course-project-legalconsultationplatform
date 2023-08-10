package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;
import entity.ApplicationException;
import driver.screen.QuestionListUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class ViewResponseFormatter implements ViewOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    public ViewResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    @Override
    public ViewResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public ViewResponseModel prepareSuccess(ViewResponseModel response) {
        int userId = response.getUserId();
        String userName = response.getUserName();
        Map<Integer, QuestionDisplayFormatter> questionMap = response.getQuestionMap();
        QuestionListUI questionListUI = new QuestionListUI(controlContainer, cardLayout, screens, userId, userName, questionMap);
        screens.add(questionListUI, "browseQuestion");
        cardLayout.show(screens, "browseQuestion");
        System.out.println("Available questions showed");
        return response;
    }
}
