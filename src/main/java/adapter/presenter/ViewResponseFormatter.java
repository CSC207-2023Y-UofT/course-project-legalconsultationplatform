package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;
import driver.screen.ApplicationException;
import driver.screen.QuestionListUI;

import javax.swing.*;
import java.awt.*;
import java.util.Map;

/**
 * The ViewResponseFormatter class is responsible for formatting and displaying view responses in the user interface.
 * It implements the ViewOutputBoundary interface to handle success and failure scenarios for presenting data to the user.
 */
public class ViewResponseFormatter implements ViewOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    /**
     * Constructs a ViewResponseFormatter with the specified card layout and screen panel.
     *
     * @param cardLayout The CardLayout used to manage different screens.
     * @param screens The main panel containing various UI screens.
     */
    public ViewResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    /**
     * Sets the ControlContainer instance to manage user interactions.
     *
     * @param controlContainer The ControlContainer instance to be set.
     */
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    /**
     * Throws an ApplicationException with the specified error message.
     *
     * @param msg The error message to be included in the exception.
     * @return This method does not return due to the thrown exception.
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
        int userId = response.getUserId();
        String userName = response.getUserName();
        Map<Integer, QuestionDisplayFormatter> questionMap = response.getQuestionMap();

        // Create a QuestionListUI to display the list of questions.
        QuestionListUI questionListUI = new QuestionListUI(controlContainer, cardLayout, screens, userId, userName, questionMap);
        screens.add(questionListUI, "browseQuestion");
        cardLayout.show(screens, "browseQuestion");

        // Print a message indicating that available questions have been displayed.
        System.out.println("Available questions showed");

        return response;
    }
}