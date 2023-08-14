package adapter.presenter;


import businessrule.UIFactory;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.PostDisplayFormatter;
import driver.screen.UIManager;
import entity.ApplicationException;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    driver.screen.UIManager UIManager;

    public TheQuestionResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }
    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        Map<Integer, PostDisplayFormatter> postMap= response.getPostMap();
        if (postMap.size() == 1){
            cardLayout.show(screens, "Home Page");
        } else {
            JPanel questionUI = UIFactory.getUI(UIFactory.UIType.QUESTION_UI, UIManager, response);
            screens.add(questionUI, "Question");
            cardLayout.show(screens, "Question");
        }
        return response;
    }
}
