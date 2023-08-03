package userclosequestion;

import questionentities.Question;
import gateway.QuestionGateway;
import userentities.User;
import gateway.UserGateway;
import gateway.UserGatewayFactory;

public class CloseQuestionInteractor implements CloseInputBoundary{
    final QuestionGateway questionGateway;
    final MessageOutputBoundary messageOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public CloseQuestionInteractor(QuestionGateway questionGateway, MessageOutputBoundary messageOutputBoundary, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.messageOutputBoundary = messageOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public MessageResponseModel closeQuestion(CloseRequestModel closeRequestModel) {
        int userId = closeRequestModel.getUserId();;
        int questionId = closeRequestModel.getQuestionId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);
        Question question = questionGateway.getQuestion(questionId);
        boolean isQuestionCloseable = user.isQuestionCloseable(question);

        if(isQuestionCloseable){
            question.setClose(true);
            questionGateway.updateIsClose(questionId, true);
            return messageOutputBoundary.prepareSuccess("The question has been succeccfully closed");
        }
        else{
            return messageOutputBoundary.prepareFail("This question cannot close");
        }

    }
}
