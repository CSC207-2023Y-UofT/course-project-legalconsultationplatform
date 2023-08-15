package usecases.interactors;

import usecases.gateway.AttorneyGateway;
import usecases.gateway.QuestionGateway;
import usecases.outputboundary.ViewOutputBoundary;
import entities.user.Attorney;
import entities.Question;
import entities.user.User;
import java.util.List;

/**
 * This class represents the interactor responsible for recommending questions to an attorney.
 * This interactor fetches recommended questions for an attorney based on their profile and preferences.
 */
public class AttorneyRecommendInteractor extends ViewQuestionInteractorBase {
    private  final AttorneyGateway attorneyGateway;

    /**
     * Constructor for AttorneyRecommendInteractor.
     *
     * @param outputBoundary The output boundary for preparing view response models.
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
     * @return The fetched attorney entity.
     */
    @Override
    protected User fetchUser(int userId) {
        return attorneyGateway.get(userId);
    }
}
