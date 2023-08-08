package businessrule.usecase;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import entity.Question;
import entity.User;
import java.util.List;
import java.util.Map;

/**
 * This class serves as an interactor for viewing questions associated with a user.
 * The class is responsible for handling user requests to view questions and constructing the appropriate response.
 */
public class ViewQuestionInteractor implements ViewInputBoundary {

    /**
     * The gateway to access question-related data.
     */
    final QuestionGateway questionGateway;

    /**
     * The output boundary for sending the response.
     */
    final ViewOutputBoundary viewOutputBoundary;

    /**
     * Factory for creating user gateways.
     */
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructs a new ViewQuestionInteractor with the provided dependencies.
     *
     * @param questionGateway    The gateway to access question-related data.
     * @param viewOutputBoundary The output boundary for sending the response.
     * @param userGatewayFactory Factory for creating user gateways.
     */
    public ViewQuestionInteractor(QuestionGateway questionGateway, ViewOutputBoundary viewOutputBoundary,
                                  UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.viewOutputBoundary = viewOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Handles the user's request to view questions and constructs the appropriate response.
     *
     * @param viewRequestModel The request model containing the user's information.
     * @return The response model containing the user's information and question data.
     */
    @Override
    public ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel) {
        // Get the ID of the user who performed the operation
        int userId = viewRequestModel.getUserId();

        // Create a user gateway using the factory
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);

        // Get the user using the user gateway
        User user = userGateway.getUser(userId);

        // Get the list of questions associated with the user
        List<Question> questionList = user.getQuestionsList();

        // Construct a map of questions for display
        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionList);

        // Create and populate the response model
        ViewResponseModel viewResponseModel = new ViewResponseModel(user.getUserId(), user.getUserName(), questionMap);

        // Prepare and return the success response using the output boundary
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }
}