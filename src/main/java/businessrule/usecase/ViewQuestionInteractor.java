package businessrule.usecase;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import entity.Attorney;
import entity.Question;
import entity.User;
import java.util.List;
import java.util.Map;

/**
 * This class represents the interactor responsible for viewing questions.
 *
 * This interactor handles the process of fetching and displaying questions based on user input
 * and preparing the appropriate response model.
 */
public class ViewQuestionInteractor extends ViewQuestionInteractorBase {
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructor for ViewQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param viewOutputBoundary The output boundary for preparing view response models.
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public ViewQuestionInteractor(QuestionGateway questionGateway, ViewOutputBoundary viewOutputBoundary,
                                  UserGatewayFactory userGatewayFactory) {
        super(viewOutputBoundary, questionGateway);
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Fetch questions for the user.
     *
     * @param viewRequestModel The request model containing user details.
     * @return The list of questions for the user.
     */
    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        User user = fetchUser(viewRequestModel);
        return user.getQuestionsList();
    }

    /**
     * Fetch the user based on the request model.
     *
     * @param viewRequestModel The request model containing user details.
     * @return The user corresponding to the request.
     */
    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int userId = viewRequestModel.getUserId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        return userGateway.get(userId);
    }
}