package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.RegisterResponseModel;

/**
 * This interface provides methods for setting the control container, preparing success responses,
 * and preparing failure responses.
 */
public interface RegisterOutputBoundary {

    /**
     * Set the control container for handling registration responses.
     *
     * @param controlContainer The control container used for handling responses.
     */
    void setControlContainer(ControlContainer controlContainer);

    /**
     * Prepare a success response for registration.
     *
     * @param msg The message indicating the success.
     * @return A prepared registration response model indicating the success.
     */
    RegisterResponseModel prepareSuccess(String msg);

    /**
     * Prepare a failure response for registration.
     *
     * @param msg The message indicating the reason for the failure.
     * @return A prepared registration response model indicating the failure.
     */
    RegisterResponseModel prepareFail(String msg);
}