package adapter.controller;

import businessrule.responsemodel.UserResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.responsemodel.ViewResponseModel;

import javax.swing.text.View;

/**
 * Controller class responsible for viewing questions.
 *
 * This class acts as an interface between the presentation layer and the business logic for viewing questions.
 * It takes a user's ID as input, creates a request model, and delegates the request to the corresponding input boundary.
 * The response model from the input boundary is returned to the caller.
 */
public class ViewQuestionControl{

    private final ViewInputBoundary viewInputBoundary;

    /**
     * Constructor to initialize the ViewQuestionControl with the corresponding input boundary.
     *
     * @param viewInputBoundary The input boundary for viewing questions.
     */
    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }

    /**
     * View a question based on the user's ID.
     *
     * @return A response model containing the information about the viewed question.
     */
    public ViewResponseModel viewQuestion(){
        return viewInputBoundary.viewQuestion();
    }
}
