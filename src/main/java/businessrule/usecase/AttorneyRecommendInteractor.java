package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import entity.Attorney;
import entity.Question;
import entity.User;
import java.util.List;

public class AttorneyRecommendInteractor extends ViewQuestionInteractorBase {
    private  final AttorneyGateway attorneyGateway;

    public AttorneyRecommendInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(viewOutputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        Attorney attorney = (Attorney) fetchUser(viewRequestModel);
        return attorney.getRecommendations();
    }

    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return attorneyGateway.get(attorneyId);
    }
}
