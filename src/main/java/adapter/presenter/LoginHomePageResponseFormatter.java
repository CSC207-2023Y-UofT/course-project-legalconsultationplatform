package adapter.presenter;

import businessrule.UIFactory;
import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;
import driver.screen.UIManager;

import javax.swing.*;
import java.awt.*;

public class LoginHomePageResponseFormatter extends HomePageResponseFormatter{

    public LoginHomePageResponseFormatter(driver.screen.UIManager UIManager) {
        super(UIManager);
    }

    @Override
    public UserResponseModel prepareSuccess(UserResponseModel response) {
        pageConstructor(response);
        return response;
    }

    @Override
    protected void pageConstructor(UserResponseModel response) {
        super.pageConstructor(response);
        JPanel questionList = UIFactory.getUI(UIFactory.UIType.QUESTION_LIST_UI, UIManager, (ViewResponseModel)response);
        UIManager.getScreens().add(questionList, "Question List");
    }
}
