package usecases.inputboundary;

import usecases.requests.RateRequestModel;
import usecases.responses.UserResponseModel;

/**
 * This interface provides a method for rating an answer based on the provided rate request model.
 */
public interface RateInputBoundary {

    /**
     * Rate an answer based on the provided rate request model.
     *
     * @param rateRequestModel The request model containing the necessary information for rating an answer.
     * @return A response model indicating the result of rating an answer, typically for updating the home page.
     */
    UserResponseModel rateAnswer(RateRequestModel rateRequestModel);

}
