package adapter.presenter;


import businessrule.UIFactory;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
import driver.screen.UIManager;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    driver.screen.UIManager UIManager;

    public TheQuestionResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }
    @Override
    public UserResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public UserResponseModel prepareSuccess(UserResponseModel response) {
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel questionUI = UIFactory.getUI(UIFactory.UIType.QUESTION_UI, UIManager, response);
        screens.add(questionUI, "Question");
        cardLayout.show(screens, "Question");

        return response;
    }
}
