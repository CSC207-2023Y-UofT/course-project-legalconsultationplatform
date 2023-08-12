package businessrule.usecase;

import businessrule.gateway.ClientGateway;
import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import entity.Client;
import entity.Question;
import entity.User;

public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final HomePageOutputBoundary homePageOutputBoundary;
    private final ClientGateway clientGateway;

    public RateInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.clientGateway = clientGateway;
    }

    @Override
    public HomePageResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();
        int userId = rateRequestModel.getUserId();

        Client user = clientGateway.get(userId);
        Question answer = questionGateway.get(answerId);

        if (answer.isClose() || answer.isTaken()) {
            String userType = "Client";
            questionGateway.updateRating(answerId, rating);
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName(), userType);
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        } else {
            return homePageOutputBoundary.prepareFail("You cannot rate this question!");
        }
    }
}
