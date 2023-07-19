package userselectquestion;

import questionentities.Question;
import questiongateway.QuestionGateway;
import screenpresenter.ScreenOutputBoundary;
import screenpresenter.ScreenResponseModel;
import usergateway.UserGateway;

public class SelectQuestionInteractor implements SelectInputBoundary{
    final QuestionGateway questionGateway;
    final ScreenOutputBoundary screenOutputBoundary;
    final UserGateway userGateway;

    public SelectQuestionInteractor(QuestionGateway questionGateway, ScreenOutputBoundary screenOutputBoundary,
                                    UserGateway userGateway) {
        this.questionGateway = questionGateway;
        this.screenOutputBoundary = screenOutputBoundary;
        this.userGateway = userGateway;
    }

    @Override
    public ScreenResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        return null;
    }
}
