package adapter.controller;

import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.UserResponseModel;

public class RateControl {
    private final RateInputBoundary rateInput;

    public RateControl(RateInputBoundary input) {
        this.rateInput = input;
    }

    public UserResponseModel rateAnswer(int rate, int questionId) {
        RateRequestModel rating = new RateRequestModel(rate, questionId);
        return rateInput.rateAnswer(rating);
    }

}
