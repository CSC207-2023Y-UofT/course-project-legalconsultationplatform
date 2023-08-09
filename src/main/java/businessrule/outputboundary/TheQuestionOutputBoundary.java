package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.TheQuestionResponseModel;

/**
 * This interface provides methods for setting the control container, preparing failure responses,
 * and preparing success responses.
 */
public interface TheQuestionOutputBoundary {

    /**
     * Set the control container for handling question responses.
     *
     * @param controlContainer The control container used for handling responses.
     */
    void setControlContainer(ControlContainer controlContainer);

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