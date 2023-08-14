package businessrule.usecase;


import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.outputboundary.TheQuestionOutputBoundary;
import businessrule.outputboundary.ViewOutputBoundary;
import entity.Question;
import entity.User;
import java.util.List;

public class ViewQuestionInteractor extends ViewQuestionInteractorBase{
    final UserGatewayFactory userGatewayFactory;

    public ViewQuestionInteractor(ViewOutputBoundary outputBoundary, QuestionGateway questionGateway, UserGatewayFactory userGatewayFactory) {
        super(outputBoundary, questionGateway);
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    protected List<Question> fetchQuestions(int userId) {
        User user = fetchUser(userId);
        return user.getQuestionsList();
    }

    @Override
    protected User fetchUser(int userId) {
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        return userGateway.get(userId);
    }

}