package userselectquestion;

import questionentities.Question;
import questiongateway.QuestionGateway;
import screenpresenter.ScreenOutputBoundary;

public class SelectQuestionInteractor implements SelectInputBoundary{
    final QuestionGateway questionGateway;
    final ScreenOutputBoundary screenOutputBoundary;

    public SelectQuestionInteractor(QuestionGateway questionGateway, ScreenOutputBoundary screenOutputBoundary) {
        this.questionGateway = questionGateway;
        this.screenOutputBoundary = screenOutputBoundary;
    }

    @Override
    public TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        return null;
    }
}
