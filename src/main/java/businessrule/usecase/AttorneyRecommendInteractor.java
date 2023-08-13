package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import entity.Attorney;
import entity.Question;
import entity.User;
import java.util.List;

public class AttorneyRecommendInteractor extends ViewQuestionInteractorBase {
    private  final AttorneyGateway attorneyGateway;

    public AttorneyRecommendInteractor(TheQuestionOutputBoundary outputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(outputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    protected List<Question> fetchQuestions(int userId) {
        Attorney attorney = (Attorney) fetchUser(userId);
        return attorney.getRecommendations();
    }

    @Override
    protected User fetchUser(int userId) {
        return attorneyGateway.get(userId);
    }
}
