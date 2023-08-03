package adapter.controller;

import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public class RateControl {
    private final RateInputBoundary rateInput;

    public RateControl(RateInputBoundary input) {
        this.rateInput = input;
    }

    HomePageResponseModel rateAnswer(int rate, int questionId, int userId) {
        RateRequestModel rating = new RateRequestModel(rate, questionId, userId);
        return rateInput.rateAnswer(rating);
    }

}
