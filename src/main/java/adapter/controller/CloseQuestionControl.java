
package adapter.controller;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
/**
 * This class represents a controller responsible for closing a question. It utilizes the CloseInputBoundary
 * to handle the process of closing a question.
 */

public class CloseQuestionControl {
    private final CloseInputBoundary closeInputBoundary;

    /**
     * Constructs a CloseQuestionControl instance with the provided CloseInputBoundary.
     *
     * @param closeInputBoundary The CloseInputBoundary implementation to be used for closing questions.
     */
    public CloseQuestionControl(CloseInputBoundary closeInputBoundary) {
        this.closeInputBoundary = closeInputBoundary;
    }

    /**
     * Initiates the process of closing a question.
     *
     * @param questionId The ID of the question to be closed.
     * @param userId The ID of the user initiating the closing process.
     * @return A HomePageResponseModel containing the result of the closing operation.
     */
    public HomePageResponseModel closeQuestion(int questionId, int userId) {
        CloseRequestModel closeRequestModel = new CloseRequestModel(questionId, userId);
        return closeInputBoundary.closeQuestion(closeRequestModel);
    }
}