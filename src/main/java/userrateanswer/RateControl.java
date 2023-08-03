package userrateanswer;

public class RateControl {
    private final RateInputBoundary rateInput;

    public RateControl(RateInputBoundary input) {
        this.rateInput = input;
    }

    MessageResponseModel rateAnswer(int rate, int questionId) {
        RateRequestModel rating = new RateRequestModel(rate, questionId);
        return rateInput.rateAnswer(rating);
    }

}
