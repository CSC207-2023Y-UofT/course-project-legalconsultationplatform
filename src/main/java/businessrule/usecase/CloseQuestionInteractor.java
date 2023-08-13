package businessrule.usecase;

import businessrule.inputboundary.CloseInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import entity.Question;
import businessrule.gateway.QuestionGateway;
import entity.User;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;

import java.time.LocalDate;

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
        // get input data
        int userId = closeRequestModel.getUserId();;
        int questionId = closeRequestModel.getQuestionId();
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);
        Question question = questionGateway.get(questionId);


        // handle close logic and prepare response model
        boolean isQuestionCloseable = user.isQuestionCloseable(question);
        if(isQuestionCloseable){
            questionGateway.updateIsClose(questionId, true);
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName(), "Client");
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        }
        else{
            return homePageOutputBoundary.prepareFail("You cannot close this question!");
        }
    }
}
