package usecases.inputboundary;

import usecases.requests.CloseRequestModel;
import usecases.responses.UserResponseModel;

/**
 * This interface provides a method for closing a question based on the provided close request model.
 */
public interface CloseInputBoundary {

    /**
     * Close a question based on the provided close request model.
     *
     * @param closeRequestModel The request model containing the necessary information for closing the question.
     * @return A response model indicating the result of closing the question, typically for updating the home page.
     */
    UserResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
