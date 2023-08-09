package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.ViewResponseModel;

/**
 * This interface provides methods for setting the control container, preparing failure responses,
 * and preparing success responses.
 */
public interface ViewOutputBoundary {

    /**
     * Set the control container for handling view responses.
     *
     * @param controlContainer The control container used for handling responses.
     */
    void setControlContainer(ControlContainer controlContainer);

    /**
     * Prepare a failure response for a view.
     *
     * @param msg The message indicating the reason for the failure.
     * @return A prepared view response model indicating the failure.
     */
    ViewResponseModel prepareFail(String msg);

    /**
     * Prepare a success response for a view.
     *
     * @param response The response model to prepare as a success response.
     * @return A prepared view response model indicating the success.
     */
    ViewResponseModel prepareSuccess(ViewResponseModel response);
}