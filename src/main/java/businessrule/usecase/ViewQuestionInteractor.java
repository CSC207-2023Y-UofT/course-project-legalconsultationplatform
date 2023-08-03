package businessrule.usecase;

import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import driver.database.QuestionGateway;
import driver.database.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.outputboundary.ViewOutputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import entity.Question;
import entity.User;
import java.util.List;

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
        int questionId = viewRequestModel.getQuestionId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);
        Question question = questionGateway.getQuestion(questionId);
        List<Question> questionList = user.getQuestionsList();
        ViewResponseModel viewResponseModel = new ViewResponseModel(); // 在presenter完成后要后加东西
        return viewOutputBoundary.prepareSuccess();
    }
}