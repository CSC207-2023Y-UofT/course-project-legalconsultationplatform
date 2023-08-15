package adapters.presenters;

import infrastructure.screens.UIFactory;
import usecases.outputboundary.ViewOutputBoundary;
import usecases.responses.ViewResponseModel;
import infrastructure.screens.utils.UIManager;
import entities.ApplicationException;
import javax.swing.*;
import java.awt.*;

/**
 * The ViewResponseFormatter class is responsible for formatting and displaying view responses in the user interface.
 * It implements the ViewOutputBoundary interface to handle success and failure scenarios for presenting data to the user.
 */
public class ViewResponseFormatter implements ViewOutputBoundary {
    UIManager UIManager;

    /**
     * Constructs a ViewResponseFormatter with the specified card layout and screen panel.
     *
     * @param UIManager UIManager class contains screens, cardlayouts, and control container.
     */
    public ViewResponseFormatter(infrastructure.screens.utils.UIManager UIManager) {
        this.UIManager = UIManager;
    }

    /**
     * Throws an ApplicationException with the specified error message.
     *
     * @param msg The error message to be included in the exception.
     * @throws ApplicationException Always throws an ApplicationException with the provided message.
     */
    @Override
    public ViewResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    /**
     * Prepares and displays a successful view response in the user interface.
     *
     * @param response The ViewResponseModel containing the data to be displayed.
     * @return The original response model passed as a parameter.
     */
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
