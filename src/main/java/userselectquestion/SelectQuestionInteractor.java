package userselectquestion;

import questionentities.Question;
import gateway.QuestionGateway;
import presenter.TheQuestionResponseModel;
import presenter.TheQuestionOutputBoundary;
import userentities.User;
import gateway.UserGateway;
import gateway.UserGatewayFactory;
import gateway.DatabaseConnection;

public class SelectQuestionInteractor implements SelectInputBoundary{
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public SelectQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                                    UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        int userId = selectRequestModel.getUserId();
        int questionId = selectRequestModel.getQuestionId();

        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);

        Question question = questionGateway.getQuestion(questionId);

        boolean isQuestionSelectable = user.isQuestionSelectable(question);

        // TODO: complete method call after finishing the presenter class
        if (isQuestionSelectable) {
            return theQuestionOutputBoundary.prepareSuccess();
        } else {
            return theQuestionOutputBoundary.prepareFail("This question is not accessible.");
        }
    }
}
