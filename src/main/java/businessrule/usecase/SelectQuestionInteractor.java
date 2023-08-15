package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.inputboundary.SelectInputBoundary;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.BuilderService;
import businessrule.usecase.util.PostDisplayFormatter;
import businessrule.usecase.util.PostMapConstructor;
import entity.Question;
import businessrule.gateway.QuestionGateway;
import businessrule.responsemodel.TheQuestionResponseModel;
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
    final TheQuestionOutputBoundary outputBoundary;
    final UserGatewayFactory userGatewayFactory;

    /**
     * Constructor for SelectQuestionInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param outputBoundary The output boundary for preparing question response models.
     * @param userGatewayFactory The factory for creating user gateways.
     */
    public SelectQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary outputBoundary, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
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
        // get input data
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int questionId = selectRequestModel.getQuestionId();

        // use gateway factory to retrieve the correct type of repo
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(response.getUserId());
        User user = userGateway.get(response.getUserId());

        // get question
        Question question = questionGateway.get(questionId);

        // handle select question logic and prepare response model
        boolean isQuestionSelectable = user.isQuestionSelectable(question);
        if (isQuestionSelectable) {
            PostMapConstructor postMapConstructor = new PostMapConstructor(userGatewayFactory);
            Map<Integer, PostDisplayFormatter> postMap = postMapConstructor.constructPostMap(question);
            TheQuestionResponseModel theQuestionResponseModel = BuilderService.getInstance().constructTheQuestionResponse(question, response, postMap);
            return outputBoundary.prepareSuccess(theQuestionResponseModel);
        } else {
            return outputBoundary.prepareFail("This question is not accessible.");
        }
    }


}
