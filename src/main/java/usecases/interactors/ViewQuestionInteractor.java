package usecases.interactors;


import usecases.gateway.QuestionGateway;
import usecases.gateway.UserGateway;
import infrastructure.database.UserGatewayFactory;
import usecases.outputboundary.ViewOutputBoundary;
import entities.Question;
import entities.user.User;
import java.util.List;

/**
 * This class represents the interactor responsible for viewing questions.
 * This interactor handles the process of fetching and displaying questions based on user input
 * and preparing the appropriate response model.
 */
public class ViewQuestionInteractor extends ViewQuestionInteractorBase{
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructor for ViewQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param outputBoundary The output boundary for preparing view response models.
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public ViewQuestionInteractor(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway, UserGatewayFactory userGatewayFactory) {
        super(outputBoundary, questionGateway);
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Fetch questions for the user.
     *
     * @return The list of questions for the user.
     */
    @Override
    protected List<Question> fetchQuestions(int userId) {
        User user = fetchUser(userId);
        return user.getQuestionsList();
    }

    /**
     * Fetch the user based on the request model.
     *
     * @return The user corresponding to the request.
     */
    @Override
    protected User fetchUser(int userId) {
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        return userGateway.get(userId);
    }

}