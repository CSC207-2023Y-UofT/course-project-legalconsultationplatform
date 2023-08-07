package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.gateway.QuestionGateway;
import entity.Question;
import entity.User;

import java.util.List;

public class BrowseQuestionInteractor extends ViewQuestionInteractorBase {
    final AttorneyGateway attorneyGateway;

    public BrowseQuestionInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(viewOutputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        return questionGateway.getNotTakenQuestion();
    }

    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return attorneyGateway.getUser(attorneyId);
    }
}
