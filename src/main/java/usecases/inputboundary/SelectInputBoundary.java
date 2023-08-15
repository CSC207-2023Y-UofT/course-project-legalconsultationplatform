package usecases.inputboundary;

import usecases.responses.TheQuestionResponseModel;
import usecases.requests.SelectRequestModel;

/**
 * This interface provides a method for selecting a question based on the provided select request model.
 */
public interface SelectInputBoundary {

    /**
     * Select a question based on the provided select request model.
     *
     * @param selectRequestModel The request model containing the necessary information for selecting a question.
     * @return A response model containing the information about the selected question.
     */
    TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel);
}