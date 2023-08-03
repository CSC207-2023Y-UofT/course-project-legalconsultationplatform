package businessrule.usecase;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import entity.Question;
import driver.database.QuestionGateway;
import entity.User;
import driver.database.UserGateway;
import businessrule.gateway.UserGatewayFactory;

public class CloseQuestionInteractor implements CloseInputBoundary {
    final QuestionGateway questionGateway;
    final HomePageOutputBoundary homePageOutputBoundary;
    final UserGatewayFactory userGatewayFactory;

    public CloseQuestionInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public HomePageResponseModel closeQuestion(CloseRequestModel closeRequestModel) {
        int userId = closeRequestModel.getUserId();;
        int questionId = closeRequestModel.getQuestionId();
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);
        Question question = questionGateway.getQuestion(questionId);
        boolean isQuestionCloseable = user.isQuestionCloseable(question);

        if(isQuestionCloseable){
            questionGateway.updateIsClose(questionId, true);
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName());
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        }
        else{
            return homePageOutputBoundary.prepareFail("This question cannot close");
        }

    }
}
