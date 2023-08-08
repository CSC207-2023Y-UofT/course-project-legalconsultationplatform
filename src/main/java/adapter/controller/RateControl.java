package adapter.controller;

import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

/**
 * This class represents a controller responsible for handling rating-related actions.
 * It interacts with the business logic layer to handle rating answers and related operations.
 */
public class RateControl {
    private final RateInputBoundary rateInput;

    /**
     * Constructs a RateControl instance with the specified RateInputBoundary implementation.
     *
     * @param input The RateInputBoundary implementation used for handling rate-related operations.
     */
    public RateControl(RateInputBoundary input) {
        this.rateInput = input;
    }

    /**
     * Rates an answer and updates the corresponding information.
     *
     * @param rate      The rating value given by the user.
     * @param questionId The ID of the question associated with the answer.
     * @param userId    The ID of the user providing the rating.
     * @return A HomePageResponseModel containing updated information after rating.
     */
    public HomePageResponseModel rateAnswer(int rate, int questionId, int userId) {
        RateRequestModel rating = new RateRequestModel(rate, questionId, userId);
        return rateInput.rateAnswer(rating);
    }
}