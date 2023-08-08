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

public class ViewQuestionInteractor implements ViewInputBoundary {
    final QuestionGateway questionGateway;
    final ViewOutputBoundary viewOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public ViewQuestionInteractor(QuestionGateway questionGateway, ViewOutputBoundary viewOutputBoundary,
                                  UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.viewOutputBoundary = viewOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel) {
        // get user who performed the operation
        int userId = viewRequestModel.getUserId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);

        // get question list to view by the user
        List<Question> questionList = user.getQuestionsList();

        // construct response model
        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionList);
        ViewResponseModel viewResponseModel = new ViewResponseModel(user.getUserId(), user.getUserName(), questionMap);
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }
}