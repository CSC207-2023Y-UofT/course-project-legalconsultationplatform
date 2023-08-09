package businessrule.outputboundary;

import adapter.controller.ControlContainer;
import businessrule.responsemodel.HomePageResponseModel;

/**
 * This interface provides methods for setting the control container, preparing failure responses,
 * and preparing success responses.
 */
public interface HomePageOutputBoundary {

    /**
     * Set the control container for handling home page responses.
     *
     * @param controlContainer The control container used for handling responses.
     */
    void setControlContainer(ControlContainer controlContainer);

    /**
     * Prepare a failure response for the home page.
     *
     * @param msg The message indicating the reason for the failure.
     * @return A prepared home page response model indicating the failure.
     */
    HomePageResponseModel prepareFail(String msg);

    /**
     * Prepare a success response for the home page.
     *
     * @param response The response model to prepare as a success response.
     * @return A prepared home page response model indicating the success.
     */
    HomePageResponseModel prepareSuccess(HomePageResponseModel response);
}