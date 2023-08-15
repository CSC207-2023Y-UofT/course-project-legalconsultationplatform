package adapters.presenters;
import infrastructure.screens.UIFactory;
import usecases.outputboundary.UserOutputBoundary;
import usecases.responses.UserResponseModel;
import infrastructure.screens.utils.UIManager;
import entities.ApplicationException;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a formatter for generating UI responses for the home page.
 * It prepares and formats the response data for display on the user interface.
 */
public class HomePageResponseFormatter implements UserOutputBoundary {
    UIManager UIManager;

    /**
     * Constructs a HomePageResponseFormatter.
     *
     * @param UIManager A UIManager class that contains the screens and cardlayouts.
     */
    public HomePageResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    /**
     * Prepares a failure response with the specified message.
     *
     * @param msg The error message.
     * @throws ApplicationException An exception indicating a failure in the application.
     */
    @Override
    public UserResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }


    /**
     * Prepares a successful response for the home page.
     *
     * @param response The response model containing the data to be displayed.
     * @return The prepared response model.
     */
    @Override
    public UserResponseModel prepareSuccess(UserResponseModel response) {
        pageConstructor(response);
        return response;
    }

    protected void pageConstructor(UserResponseModel response){
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel homePage = UIFactory.getUI(UIFactory.UIType.HOME_PAGE, UIManager, response);
        screens.add(homePage, "Home Page");
        cardLayout.show(screens, "Home Page");
    }
}
