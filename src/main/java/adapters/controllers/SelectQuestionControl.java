package adapters.controllers;

import usecases.inputboundary.SelectInputBoundary;
import usecases.requests.SelectRequestModel;
import usecases.responses.TheQuestionResponseModel;

/**
 * This class represents a controller responsible for selecting a question.
 * It interacts with the business logic through the SelectInputBoundary and handles the input/output for question selection.
 */
public class SelectQuestionControl {
    private final SelectInputBoundary selectInputBoundary;

    /**
     * Constructs a SelectQuestionControl instance with the given SelectInputBoundary.
     *
     * @param selectInputBoundary The input boundary responsible for selecting questions.
     */
    public SelectQuestionControl(SelectInputBoundary selectInputBoundary) {
        this.selectInputBoundary = selectInputBoundary;
    }

    /**
     * Selects a question based on the provided question ID and user ID.
     *
     * @param questionId The ID of the question to be selected.
     * @return The response model containing the selected question's information.
     */
    public TheQuestionResponseModel selectQuestion(int questionId) {
        SelectRequestModel selectRequestModel =  new SelectRequestModel(questionId);
        return selectInputBoundary.selectQuestion(selectRequestModel);
    }
}
