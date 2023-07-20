package userclosequestion;

import presenter.TheQuestionOutputBoundary;
import presenter.TheQuestionResponseModel;
import questiongateway.QuestionGateway;
import usergateway.UserGatewayFactory;

public class CloseQuestionInteractor implements CloseInputBoundary{
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public CloseQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                                   UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public TheQuestionResponseModel closeQuestion(CloseRequestModel colseRequestModel) {
        return null;
    }
}
