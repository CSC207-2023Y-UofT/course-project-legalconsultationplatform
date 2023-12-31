package adapters.presenters;


import infrastructure.screens.UIFactory;
import usecases.outputboundary.TheQuestionOutputBoundary;
import usecases.responses.TheQuestionResponseModel;
import infrastructure.screens.utils.UIManager;
import entities.ApplicationException;
import usecases.session.SessionManager;

import javax.swing.*;
import java.awt.*;

/**
 * This class represents the  formatting the response model of a question into a user interface representation
 * for display.
 */

public class TheQuestionResponseFormatter implements TheQuestionOutputBoundary {
    infrastructure.screens.utils.UIManager UIManager;

    /**
     * Constructs a TheQuestionResponseFormatter with the specified CardLayout and JPanel.
     *
     * @param UIManager UIManager class contains screens, cardlayouts, and control container
     */
    public TheQuestionResponseFormatter(UIManager UIManager) {
        this.UIManager = UIManager;
    }

    /**
     * Prepares a failure response by throwing an ApplicationException with the specified message.
     *
     * @param msg The error message.
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
        JPanel screens = UIManager.getScreens();
        CardLayout cardLayout = UIManager.getCardLayout();
        JPanel questionUI = UIFactory.getUI(UIFactory.UIType.QUESTION_UI, UIManager, response);
        JPanel questionListUI = UIFactory.getUI(UIFactory.UIType.QUESTION_LIST_UI, UIManager, SessionManager.getSession().getUserResponseModel());
        screens.add(questionUI, "Question");
        screens.add(questionListUI, "Question List");
        cardLayout.show(screens, "Question");
        return response;
    }
}
