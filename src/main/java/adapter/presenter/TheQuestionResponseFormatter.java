
package adapter.presenter;

import adapter.controller.ControlContainer;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.usecase.PostDisplayFormatter;
import driver.screen.ApplicationException;
import driver.screen.TheQuestionUI;

import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.Map;

/**
 * This class represents the  formatting the response model of a question into a user interface representation
 * for display.
 */

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    CardLayout cardLayout;
    JPanel screens;
    ControlContainer controlContainer;

    /**
     * Constructs a TheQuestionResponseFormatter with the specified CardLayout and JPanel.
     *
     * @param cardLayout The CardLayout used to manage different screens.
     * @param screens The JPanel where the screens are displayed.
     */
    public TheQuestionResponseFormatter(CardLayout cardLayout, JPanel screens) {
        this.cardLayout = cardLayout;
        this.screens = screens;
    }

    /**
     * Sets the ControlContainer to interact with the application's control logic.
     *
     * @param controlContainer The ControlContainer instance.
     */
    @Override
    public void setControlContainer(ControlContainer controlContainer) {
        this.controlContainer = controlContainer;
    }

    /**
     * Prepares a failure response by throwing an ApplicationException with the specified message.
     *
     * @param msg The error message.
     * @return This method does not return as it throws an exception.
     * @throws ApplicationException Always thrown with the specified error message.
     */
    @Override
    public TheQuestionResponseModel prepareFail(String msg) {
        throw new ApplicationException(msg);
    }

    /**
     * Prepares a success response by formatting the response model into a user interface representation
     * for display in TheQuestionUI.
     *
     * @param response The response model.
     * @return The formatted response model suitable for display.
     */
    @Override
    public TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response) {
        int userId = response.getUserId();
        String userName = response.getUserName();
        int questionId = response.getQuestionId();
        String title = response.getTitle();
        String type = response.getType();
        LocalDate deadline = response.getDeadline();
        Map<Integer, PostDisplayFormatter> postMap = response.getPostMap();

        TheQuestionUI questionUI = new TheQuestionUI(controlContainer, cardLayout, screens, userId, userName,
                questionId,  title, type, deadline, postMap);
        screens.add(questionUI, "question");
        cardLayout.show(screens, "question");
        return response;
    }
}