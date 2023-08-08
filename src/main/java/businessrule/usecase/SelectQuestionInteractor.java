package businessrule.usecase;

import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import entity.Question;
import businessrule.gateway.QuestionGateway;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import entity.User;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import java.util.Map;

/**
 * This class represents selecting a specific question by a user in the system.
 */
public class SelectQuestionInteractor implements SelectInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    /**
     * Creates a new SelectQuestionInteractor instance.
     *
     * @param questionGateway The gateway for retrieving question-related data.
     * @param theQuestionOutputBoundary The output boundary for returning the result of the question selection.
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public SelectQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                                    UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Selects a specific question by a user in the system.
     *
     * @param selectRequestModel The request model containing the user's ID and the ID of the question to be selected.
     * @return A response model containing information about the selected question and its related details.
     */
    @Override
    public TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        // get input data
        int userId = selectRequestModel.getUserId();
        int questionId = selectRequestModel.getQuestionId();

        // use gateway factory to retrieve the correct type of repo
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);

        // get question
        Question question = questionGateway.getQuestion(questionId);

        // handle select question logic and prepare response model
        boolean isQuestionSelectable = user.isQuestionSelectable(question);
        if (isQuestionSelectable) {
            PostMapConstructor postMapConstructor = new PostMapConstructor(userGatewayFactory);
            Map<Integer, PostDisplayFormatter> postMap = postMapConstructor.constructPostMap(question);
            TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(userId, questionId,
                    user.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(), postMap);
            return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
        } else {
            return theQuestionOutputBoundary.prepareFail("This question is not accessible.");
        }
    }
}