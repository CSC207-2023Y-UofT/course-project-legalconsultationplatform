package businessrule.inputboundary;

import businessrule.responsemodel.UserResponseModel;
import businessrule.responsemodel.ViewResponseModel;

/**
 * This interface provides a method for viewing a question based on the provided view request model.
 */
public interface ViewInputBoundary {

    /**
     * View a question based on the provided view request model.
     *
     * @return A response model containing the information about the viewed question.
     */
    ViewResponseModel viewQuestion();
}
