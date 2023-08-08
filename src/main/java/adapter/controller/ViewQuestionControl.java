package adapter.controller;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;

/**
 * This class represents a controller responsible for viewing questions.
 * It interacts with the business logic layer to retrieve and provide
 * question-related information to the view.
 */
public class ViewQuestionControl {

    private final ViewInputBoundary viewInputBoundary;

    /**
     * Constructs a new ViewQuestionControl instance.
     *
     * @param viewInputBoundary The input boundary for viewing questions,
     *                          responsible for handling interactions between
     *                          the controller and the business logic layer.
     */
    public ViewQuestionControl(ViewInputBoundary viewInputBoundary) {
        this.viewInputBoundary = viewInputBoundary;
    }

    /**
     * Retrieves question-related information for a specific user.
     *
     * @param userId The identifier of the user for whom the questions are to be viewed.
     * @return A response model containing the information about the questions
     *         that are accessible to the specified user.
     */
    public ViewResponseModel viewQuestion(int userId) {
        // Create a request model with the given user ID
        ViewRequestModel viewRequestModel = new ViewRequestModel(userId);

        // Delegate the request to the input boundary and return the response
        return viewInputBoundary.viewQuestion(viewRequestModel);
    }
}