package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.ClientGateway;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.UserResponseModel;
import entity.Client;
import entity.Question;
import businessrule.gateway.QuestionGateway;

public class CloseQuestionInteractor implements CloseInputBoundary {
    final QuestionGateway questionGateway;
    final UserOutputBoundary outputBoundary;
    final ClientGateway clientGateway;

    public CloseQuestionInteractor(QuestionGateway questionGateway, UserOutputBoundary outputBoundary, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.clientGateway = clientGateway;
    }

    @Override
    public UserResponseModel closeQuestion(CloseRequestModel closeRequestModel) {
        // get input data
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int questionId = closeRequestModel.getQuestionId();
        Client client = clientGateway.get(response.getUserId());
        Question question = questionGateway.get(questionId);


        // handle close logic and prepare response model
        if(question.isTaken()){
            questionGateway.updateIsClose(questionId, true);
            return outputBoundary.prepareSuccess(response);
        }
        else{
            return outputBoundary.prepareFail("You can only close this question once it was taken by attorney.");
        }
    }
}
