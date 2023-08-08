package businessrule.usecase;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import entity.Question;
import businessrule.gateway.QuestionGateway;
import entity.User;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;

/**
 * This class represents a use case interactor for closing a question.
 *
 * This class is responsible for handling the business logic related to closing a question in the system.
 */
public class CloseQuestionInteractor implements CloseInputBoundary {

    /**
     * The gateway for accessing question-related operations.
     */
    final QuestionGateway questionGateway;

    /**
     * The output boundary for preparing responses to the home page.
     */
    final HomePageOutputBoundary homePageOutputBoundary;

    /**
     * The factory for creating UserGateway instances.
     */
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructs an instance of CloseQuestionInteractor with the necessary dependencies.
     *
     * @param questionGateway       The gateway for question-related operations.
     * @param homePageOutputBoundary  The output boundary for preparing responses to the home page.
     * @param userGatewayFactory    The factory for creating UserGateway instances.
     */
    public CloseQuestionInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Closes a question based on the provided CloseRequestModel and returns a response model for the home page.
     *
     * @param closeRequestModel The model containing information to close a question.
     * @return The response model for the home page indicating the outcome of the close attempt.
     */
    @Override
    public HomePageResponseModel closeQuestion(CloseRequestModel closeRequestModel) {
        // get input data
        int userId = closeRequestModel.getUserId();;
        int questionId = closeRequestModel.getQuestionId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);
        Question question = questionGateway.get(questionId);

        // handle close logic and prepare response model
        boolean isQuestionCloseable = user.isQuestionCloseable(question);
        if(isQuestionCloseable){
            questionGateway.updateIsClose(questionId, true);
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName(), "Client");
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        }
        else{
            return homePageOutputBoundary.prepareFail("You cannot close this question!");
        }

    }
}
