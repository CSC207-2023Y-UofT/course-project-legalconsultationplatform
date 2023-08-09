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

/**
 * This class represents the interactor responsible for browsing available questions.
 *
 * This interactor fetches a list of available questions that have not been taken and are not closed.
 */
public class BrowseQuestionInteractor extends ViewQuestionInteractorBase {
    final AttorneyGateway attorneyGateway;

    /**
     * Constructor for BrowseQuestionInteractor.
     *
     * @param viewOutputBoundary The output boundary for preparing view response models.
     * @param questionGateway The gateway for managing question entities.
     * @param attorneyGateway The gateway for managing attorney entities.
     */
    public BrowseQuestionInteractor(ViewOutputBoundary viewOutputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(viewOutputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Fetch available questions that have not been taken and are not closed.
     *
     * @param viewRequestModel The request model containing user details.
     * @return The list of available questions.
     */
    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        List<Question> notTakenList = questionGateway.getNotTakenQuestion();
        List<Question> notCloseList = questionGateway.getNotClosedQuestion();
        Set<Question> notTakenSet = new HashSet<>(notTakenList);
        Set<Question> notCloseSet = new HashSet<>(notCloseList);
        notCloseSet.retainAll(notTakenSet);
        return new ArrayList<>(notCloseSet);
    }

    /**
     * Fetch the user based on the provided user ID.
     *
     * @param viewRequestModel The request model containing the user ID.
     * @return The fetched user entity.
     */
    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return attorneyGateway.get(attorneyId);
    }
}