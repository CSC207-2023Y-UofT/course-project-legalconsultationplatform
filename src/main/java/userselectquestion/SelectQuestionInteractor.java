package userselectquestion;

import questionentities.Question;
import questiongateway.QuestionGateway;
import screenpresenter.ScreenOutputBoundary;
import screenpresenter.ScreenResponseModel;
import userentities.User;
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
        int userId = selectRequestModel.getUserId();
        int questionId = selectRequestModel.getQuestionId();

        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);

        Question question = questionGateway.getQuestion(questionId);

        boolean isQuestionSelectable = user.isQuestionSelectable(question);
        if (isQuestionSelectable) {
            return screenOutputBoundary.prepareSuccess();
        } else {
            return screenOutputBoundary.prepareFail("This question is not accessible.");
        }
    }
}
