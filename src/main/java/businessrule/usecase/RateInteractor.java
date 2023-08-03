package businessrule.usecase;

import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import driver.database.QuestionGateway;
import entity.Question;

public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final MessageOutputBoundary messageOutputBoundary;

    public RateInteractor(QuestionGateway questionGateway, MessageOutputBoundary messageOutputBoundary) {
        this.questionGateway = questionGateway;
        this.messageOutputBoundary = messageOutputBoundary;
    }

    @Override
    public MessageResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();

        Question answer = questionGateway.getQuestion(answerId);
        boolean closed = answer.isClose();

        // TODO: complete after finishing the presenter classes.
        if (closed) {
            answer.setRating(rating);
            questionGateway.updateRating(answerId, rating);
            return messageOutputBoundary.prepareSuccess("Rating successful!");
        } else {
            return messageOutputBoundary.prepareFail("This question is not closed.");
        }

    }

}
