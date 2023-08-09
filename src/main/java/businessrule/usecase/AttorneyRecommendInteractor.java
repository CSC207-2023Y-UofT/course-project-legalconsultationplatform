package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.ViewQuestionInteractorBase;
import entity.Attorney;
import entity.Question;
import entity.User;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * This class represents the interactor responsible for recommending questions to an attorney.
 *
 * This interactor fetches recommended questions for an attorney based on their profile and preferences.
 */
public class AttorneyRecommendInteractor extends ViewQuestionInteractorBase {
    private final AttorneyGateway attorneyGateway;

    /**
     * Constructor for AttorneyRecommendInteractor.
     *
     * @param viewOutputBoundary The output boundary for preparing view response models.
     * @param questionGateway The gateway for managing question entities.
     * @param attorneyGateway The gateway for managing attorney entities.
     */
    public AttorneyRecommendInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(viewOutputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Fetch recommended questions for an attorney.
     *
     * @param viewRequestModel The request model containing the attorney's user ID.
     * @return The list of recommended questions.
     */
    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        Attorney attorney = (Attorney) fetchUser(viewRequestModel);
        return attorney.getRecommendations();
    }

    /**
     * Fetch the attorney based on the provided user ID.
     *
     * @param viewRequestModel The request model containing the attorney's user ID.
     * @return The fetched attorney entity.
     */
    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return attorneyGateway.get(attorneyId);
    }
}