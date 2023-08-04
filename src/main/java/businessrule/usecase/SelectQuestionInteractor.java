package businessrule.usecase;

import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import entity.Question;
import driver.database.QuestionGateway;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import entity.User;
import driver.database.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import java.util.Map;

/**
 * The "SelectQuestionInteractor" class is part of the business rule use case layer and serves as an implementation of the
 *
 * "SelectInputBoundary" interface. It is responsible for handling the selection of a specific question by a user in the system.
 * The class uses various gateways and output boundaries to interact with the database and prepare the response model for the
 * selected question.
 */
public class SelectQuestionInteractor implements SelectInputBoundary {
    final QuestionGateway questionGateway;
    final TheQuestionOutputBoundary theQuestionOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public SelectQuestionInteractor(QuestionGateway questionGateway, TheQuestionOutputBoundary theQuestionOutputBoundary,
                                    UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.theQuestionOutputBoundary = theQuestionOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel) {
        int userId = selectRequestModel.getUserId();
        int questionId = selectRequestModel.getQuestionId();

        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);

        Question question = questionGateway.getQuestion(questionId);

        boolean isQuestionSelectable = user.isQuestionSelectable(question);
        if (isQuestionSelectable) {
            PostMapConstructor postMapConstructor = new PostMapConstructor(userGateway);
            Map<Integer, PostDisplayFormatter> postMap = postMapConstructor.constructPostMap(question);
            TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(userId, user.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(), postMap);
            return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
        } else {
            return theQuestionOutputBoundary.prepareFail("This question is not accessible.");
        }
    }
}
