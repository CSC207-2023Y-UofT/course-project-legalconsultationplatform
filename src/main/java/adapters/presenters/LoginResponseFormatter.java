package adapters.presenters;

import entities.ApplicationException;
import infrastructure.screens.UIFactory;
import infrastructure.screens.utils.UIManager;
import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;

import javax.swing.*;
import java.awt.*;

public class LoginResponseFormatter implements ViewOutputBoundary {
    UIManager uiManager;

    /**
     * Constructs a login response formatter.
     *
     * @param uiManager A UIManager class that contains the screens and cardlayouts.
     */
    public LoginResponseFormatter(UIManager uiManager) {
        this.uiManager = uiManager;
    }

    /**
     * Prepares a successful response for log in.
     *
     * @param response The response model containing the data to be displayed.
     * @return The prepared response model.
     */
    @Override
    public ViewResponseModel prepareSuccess(ViewResponseModel response) {
        JPanel screens = uiManager.getScreens();
        CardLayout cardLayout = uiManager.getCardLayout();
        UserResponseModel userResponseModel = new UserResponseModel(response.getUserId(), response.getUserName(), response.getUserType());
        JPanel homePage = UIFactory.getUI(UIFactory.UIType.HOME_PAGE, uiManager, userResponseModel);
        JPanel questionList = UIFactory.getUI(UIFactory.UIType.QUESTION_LIST_UI, uiManager, response);
        screens.add(homePage, "Home Page");
        screens.add(questionList, "Question List");
        cardLayout.show(screens, "Home Page");
        return response;
    }

    /**
     * Prepares a failure response with the specified message.
     *
     * @param msg The error message.
     * @throws ApplicationException An exception indicating a failure in the application.
     */
    @Override
    public ViewResponseModel prepareFail(String msg) {throw new ApplicationException(msg);}
}
