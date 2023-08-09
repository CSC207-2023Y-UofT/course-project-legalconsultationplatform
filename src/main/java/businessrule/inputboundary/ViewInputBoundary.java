package businessrule.inputboundary;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.requestmodel.ViewRequestModel;

/**
 * This interface provides a method for viewing a question based on the provided view request model.
 */
public interface ViewInputBoundary {

    /**
     * View a question based on the provided view request model.
     *
     * @param viewRequestModel The request model containing the necessary information for viewing a question.
     * @return A response model containing the information about the viewed question.
     */
    ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel);
}