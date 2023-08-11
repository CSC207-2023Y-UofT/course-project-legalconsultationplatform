package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.responsemodel.HomePageResponseModel;
import entity.ApplicationException;
import driver.screen.AttorneyHomePageUI;
import driver.screen.ClientHomePageUI;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a formatter for generating UI responses for the home page.
 * It prepares and formats the response data for display on the user interface.
 */
public class HomePageResponseFormatter implements HomePageOutputBoundary {

    private CardLayout cardLayout;
    private JPanel screens;
    private ControlContainer controlContainer;

    /**
     * Constructs a HomePageResponseFormatter.
     *
     * @param cardLayout      The CardLayout used for managing UI panels.
     * @param screens         The JPanel containing different UI screens.
     */
    public HomePageResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    /**
     * Sets the control container for interacting with the application.
     *
     * @param controlContainer The control container instance.
     */
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    /**
     * Prepares a failure response with the specified message.
     *
     * @param msg The error message.
     * @throws ApplicationException An exception indicating a failure in the application.
     */
    @Override
    public HomePageResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    /**
     * Prepares a successful response for the home page.
     *
     * @param response The response model containing the data to be displayed.
     * @return The prepared response model.
     */
    @Override
    public HomePageResponseModel prepareSuccess(HomePageResponseModel response) {
        int userId = response.getUserId();
        String userName = response.getUserName();
        String userType = response.getUserType();

        if (userType.equals("Attorney")) {
            AttorneyHomePageUI attorneyHomePage = new AttorneyHomePageUI(controlContainer, cardLayout, screens,
                    userId, userName);
            screens.add(attorneyHomePage, "AttorneyHomePage");
            cardLayout.show(screens, "AttorneyHomePage");
        } else {
            ClientHomePageUI clientHomePage = new ClientHomePageUI(controlContainer, cardLayout, screens,
                    userId, userName);
            screens.add(clientHomePage, "ClientHomePage");
            cardLayout.show(screens, "ClientHomePage");
        }

        return response;
    }
}