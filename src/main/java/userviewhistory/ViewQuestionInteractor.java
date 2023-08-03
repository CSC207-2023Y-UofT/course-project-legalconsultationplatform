package userviewhistory;

import gateway.QuestionGateway;
import gateway.UserGateway;
import gateway.UserGatewayFactory;
import apapter.presenter.ViewOutputBoundary;
import apapter.responsemodel.ViewResponseModel;
import questionentities.Question;
import userentities.User;
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