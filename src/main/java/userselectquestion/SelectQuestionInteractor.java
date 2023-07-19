package userselectquestion;

import questionentities.Question;
import questiongateway.QuestionGateway;
import screenpresenter.ScreenOutputBoundary;
import screenpresenter.ScreenResponseModel;
import usergateway.UserGateway;
import usergateway.UserGatewayFactory;

public class SelectQuestionInteractor implements SelectInputBoundary{
    final QuestionGateway questionGateway;
    final ScreenOutputBoundary screenOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public SelectQuestionInteractor(QuestionGateway questionGateway, ScreenOutputBoundary screenOutputBoundary,
                                    UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.screenOutputBoundary = screenOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public ScreenResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        return null;
    }
}
