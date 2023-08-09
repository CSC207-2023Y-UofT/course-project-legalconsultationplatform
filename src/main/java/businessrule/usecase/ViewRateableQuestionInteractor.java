package businessrule.usecase;

import businessrule.gateway.AttorneyGateway;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import entity.Client;
import entity.Question;
import entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents the interactor class for viewing rateable questions.
 */
public class ViewRateableQuestionInteractor extends ViewQuestionInteractorBase {

    private final ClientGateway clientGateway;

    /**
     * Constructor to initialize the ViewRateableQuestionInteractor.
     *
     * @param viewOutputBoundary The output boundary for displaying the results.
     * @param questionGateway    Gateway for accessing question-related data.
     * @param clientGateway      Gateway for accessing client-related data.
     */
    public ViewRateableQuestionInteractor(ViewOutputBoundary viewOutputBoundary,
                                          QuestionGateway questionGateway,
                                          ClientGateway clientGateway) {
        super(viewOutputBoundary, questionGateway);
        this.clientGateway = clientGateway;
    }

    /**
     * Fetches rateable questions based on the provided request model.
     *
     * @param viewRequestModel The request model containing necessary information.
     * @return List of rateable questions.
     */
    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        Client client = (Client) fetchUser(viewRequestModel);
        List<Question> questionList = client.getQuestionsList();
        List<Question> rateableList = new ArrayList<>();
        for (Question question : questionList) {
            if (question.isClose() || question.isTaken()) {
                rateableList.add(question);
            }
        }
        return rateableList;
    }

    /**
     * Fetches the user (client) associated with the given request model.
     *
     * @param viewRequestModel The request model containing the user ID.
     * @return The client associated with the provided user ID.
     */
    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int attorneyId = viewRequestModel.getUserId();
        return clientGateway.get(attorneyId);
    }
}