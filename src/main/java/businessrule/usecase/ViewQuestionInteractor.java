package businessrule.usecase;


import businessrule.requestmodel.ViewRequestModel;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.outputboundary.ViewOutputBoundary;
import entity.Question;
import entity.User;
import java.util.List;

public class ViewQuestionInteractor extends ViewQuestionInteractorBase{
    final UserGatewayFactory userGatewayFactory;

    public ViewQuestionInteractor(QuestionGateway questionGateway, ViewOutputBoundary viewOutputBoundary,
                                  UserGatewayFactory userGatewayFactory) {
        super(viewOutputBoundary, questionGateway);
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    protected List<Question> fetchQuestions(ViewRequestModel viewRequestModel) {
        User user = fetchUser(viewRequestModel);
        return user.getQuestionsList();
    }

    @Override
    protected User fetchUser(ViewRequestModel viewRequestModel) {
        int userId = viewRequestModel.getUserId();
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        return userGateway.get(userId);
    }

}