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
    public BrowseQuestionInteractor(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway, AttorneyGateway attorneyGateway) {
        super(outputBoundary, questionGateway);
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Fetch available questions that have not been taken and are not closed.
     *
     * @param userId
     * @return The list of available questions.
     */
    @Override
    protected List<Question> fetchQuestions(int userId) {
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
     * @param userId
     * @return The fetched user entity.
     */
    @Override
    protected User fetchUser(int userId) {
        return attorneyGateway.get(userId);
    }
}
