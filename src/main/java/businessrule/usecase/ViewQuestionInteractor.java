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
        int userId= viewRequestModel.getUserId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);
        List<Question> questionList = user.getQuestionsList();
        QuestionMapConstructor questionMapConstructor = new QuestionMapConstructor();
        Map<Integer, QuestionDisplayFormatter> questionMap = questionMapConstructor.constructQuestionMap(questionList);
        Attorney attorney = (Attorney) userGateway.getUser(viewRequestModel.getUserId());
        ViewResponseModel viewResponseModel = new ViewResponseModel(attorney.getUserId(), attorney.getUserName(), questionMap);
        return viewOutputBoundary.prepareSuccess(viewResponseModel);
    }
}