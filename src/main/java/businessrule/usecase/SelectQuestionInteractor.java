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
            TheQuestionResponseModel theQuestionResponseModel = new TheQuestionResponseModel(userId, questionId, user.getUserName(), question.getTitle(), question.getType(), question.getLegalDeadline(), postMap);
            return theQuestionOutputBoundary.prepareSuccess(theQuestionResponseModel);
        } else {
            return theQuestionOutputBoundary.prepareFail("This question is not accessible.");
        }
    }


}
