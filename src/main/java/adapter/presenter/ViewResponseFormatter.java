package adapter.presenter;

import businessrule.UIFactory;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import driver.screen.UIManager;
import entity.ApplicationException;
import javax.swing.*;
import java.awt.*;

public class ViewResponseFormatter implements ViewOutputBoundary {
    UIManager UIManager;

    public ViewResponseFormatter(driver.screen.UIManager UIManager) {
        this.UIManager = UIManager;
    }
    @Override
    public ViewResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    @Override
    public ViewResponseModel prepareSuccess(ViewResponseModel response) {
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel questionListUI = UIFactory.getUI(UIFactory.UIType.QUESTION_LIST_UI, UIManager, response);
        screens.add(questionListUI, "Question List");
        cardLayout.show(screens, "Question List");

        return response;
    }
}
