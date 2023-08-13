package businessrule.usecase;

import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import entity.Client;
import entity.Question;
import entity.User;

import java.util.ArrayList;
import java.util.List;

public class ViewRateableQuestionInteractor extends ViewQuestionInteractorBase{
    final ClientGateway clientGateway;

    public ViewRateableQuestionInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, ClientGateway clientGateway) {
        super(viewOutputBoundary, questionGateway);
        this.clientGateway = clientGateway;
    }

    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        Client client = (Client) fetchUser(viewRequestModel);
        List<Question> questionList = client.getQuestionsList();
        List<Question> rateableList = new ArrayList<>();
        for (Question question : questionList) {
            if (question.isClose() || question.isTaken()) {
                rateableList.add(question);
            }
        } return rateableList;
    }

    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return clientGateway.get(attorneyId);
    }
}
