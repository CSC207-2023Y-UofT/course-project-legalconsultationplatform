package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;

/**
 * This interface provides methods for setting the control container, preparing failure responses,
 * and preparing success responses.
 */
public interface TheQuestionOutputBoundary {
    /**
     * Prepare a failure response for a question.
     *
     * @param msg The message indicating the reason for the failure.
     * @return A prepared question response model indicating the failure.
     */
    TheQuestionResponseModel prepareFail(String msg);

    /**
     * Prepare a success response for a question.
     *
     * @param response The response model to prepare as a success response.
     * @return A prepared question response model indicating the success.
     */
    TheQuestionResponseModel prepareSuccess(TheQuestionResponseModel response);
}
