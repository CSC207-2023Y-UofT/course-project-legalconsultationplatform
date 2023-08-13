package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import entity.Question;
import entity.User;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class BrowseQuestionInteractor extends ViewQuestionInteractorBase {
    final AttorneyGateway attorneyGateway;

    public BrowseQuestionInteractor(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(outputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    protected List<Question> fetchQuestions(int userId) {
        List<Question> notTakenList = questionGateway.getNotTakenQuestion();
        List<Question> notCloseList = questionGateway.getNotClosedQuestion();
        Set<Question> notTakenSet = new HashSet<>(notTakenList);
        Set<Question> notCloseSet = new HashSet<>(notCloseList);
        notCloseSet.retainAll(notTakenSet);
        return new ArrayList<>(notCloseSet);
    }

    @Override
    protected User fetchUser(int userId) {
        return attorneyGateway.get(userId);
    }
}
