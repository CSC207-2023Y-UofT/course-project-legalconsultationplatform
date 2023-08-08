package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.RegisterOutputBoundary;
import businessrule.responsemodel.RegisterResponseModel;
import driver.screen.ApplicationException;
import driver.screen.WelcomeUI;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents a formatter for register response data and controlling the display of UI
 * elements based on the response.
 */
public class RegisterResponseFormatter implements RegisterOutputBoundary {
    private CardLayout cardLayout;
    private JPanel screens;
    private ControlContainer controlContainer;

    /**
     * Constructs a RegisterResponseFormatter with the specified card layout and screens panel.
     *
     * @param cardLayout The CardLayout used for managing UI screens.
     * @param screens The JPanel containing the UI screens.
     */
    public RegisterResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    /**
     * Sets the ControlContainer instance to be used for controlling the application.
     *
     * @param controlContainer The ControlContainer instance.
     */
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    /**
     * Prepares a failure response for registration.
     *
     * @param msg The error message to be displayed.
     * @return A RegisterResponseModel representing the failure response.
     * @throws ApplicationException An exception indicating the application encountered an error.
     */
    @Override
    public RegisterResponseModel prepareFail(String msg) throws ApplicationException {
        throw new ApplicationException(msg);
    }

    /**
     * Prepares a success response for registration.
     *
     * @param msg The success message to be displayed.
     * @return A RegisterResponseModel representing the success response.
     */
    @Override
    public RegisterResponseModel prepareSuccess(String msg) {
        WelcomeUI welcomeUI = new WelcomeUI(controlContainer, cardLayout, screens);
        screens.add(welcomeUI, "Welcome");
        cardLayout.show(screens, "Welcome");
        JOptionPane.showMessageDialog(null, msg);
        return new RegisterResponseModel();
    }
}