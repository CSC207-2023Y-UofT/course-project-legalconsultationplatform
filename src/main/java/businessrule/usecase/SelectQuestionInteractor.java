package businessrule.usecase;

import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.PostMapConstructor;
import entity.Question;
import businessrule.gateway.QuestionGateway;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import entity.User;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import java.util.Map;

/**
 * This class represents the interactor responsible for selecting a question.
 *
 * This interactor handles the process of selecting a question based on user input and
 * preparing and formatting the response model.
 */
public class SelectQuestionInteractor implements SelectInputBoundary {

    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructor for SelectQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param theQuestionOutputBoundary The output boundary for preparing question response models.
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public SelectQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                                    UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Select a question and prepare the response model.
     *
     * @param selectRequestModel The request model containing selection details.
     * @return The response model for the selected question.
     */
    @Override
    public TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        int userId = selectRequestModel.getUserId();
        int questionId = selectRequestModel.getQuestionId();

        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);

        Question question = questionGateway.get(questionId);

        boolean isQuestionSelectable = user.isQuestionSelectable(question);
        if (isQuestionSelectable) {
            PostMapConstructor postMapConstructor = new PostMapConstructor(userGatewayFactory);
            Map<Integer, PostDisplayFormatter> postMap = postMapConstructor.constructPostMap(question);
            TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(userId, questionId, user.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(), question.isClose(), postMap);
            return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
        } else {
            return theQuestionOutputBoundary.prepareFail("This question is not accessible.");
        }
    }
}