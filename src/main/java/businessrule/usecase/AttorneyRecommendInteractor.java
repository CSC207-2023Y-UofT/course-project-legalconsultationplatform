package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import entity.Attorney;
import entity.Question;
import entity.User;
import java.util.List;

/**
 * This class represents the interactor responsible for recommending questions to an attorney.
 *
 * This interactor fetches recommended questions for an attorney based on their profile and preferences.
 */
public class AttorneyRecommendInteractor extends ViewQuestionInteractorBase {
    private  final AttorneyGateway attorneyGateway;

    /**
     * Constructor for AttorneyRecommendInteractor.
     *
     * @param viewOutputBoundary The output boundary for preparing view response models.
     * @param questionGateway The gateway for managing question entities.
     * @param attorneyGateway The gateway for managing attorney entities.
     */
    public AttorneyRecommendInteractor(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(outputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Fetch recommended questions for an attorney.
     *
     * @param viewRequestModel The request model containing the attorney's user ID.
     * @return The list of recommended questions.
     */
    @Override
    protected List<Question> fetchQuestions(int userId) {
        Attorney attorney = (Attorney) fetchUser(userId);
        return attorney.getRecommendations();
    }

    /**
     * Fetch the attorney based on the provided user ID.
     *
     * @param viewRequestModel The request model containing the attorney's user ID.
     * @return The fetched attorney entity.
     */
    @Override
    protected User fetchUser(int userId) {
        return attorneyGateway.get(userId);
    }
}
