package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.gateway.QuestionGateway;
import entity.Question;
import entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrowseQuestionInteractor extends ViewQuestionInteractorBase {
    final AttorneyGateway attorneyGateway;

    public BrowseQuestionInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(viewOutputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        List<Question> notTakenList = questionGateway.getNotTakenQuestion();
        List<Question> notCloseList = questionGateway.getNotClosedQuestion();
        Set<Question> notTakenSet = new HashSet<>(notTakenList);
        Set<Question> notCloseSet = new HashSet<>(notCloseList);
        notCloseSet.retainAll(notTakenSet);
        return new ArrayList<>(notCloseSet);
    }

    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return attorneyGateway.get(attorneyId);
    }
}
