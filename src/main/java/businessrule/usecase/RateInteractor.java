package businessrule.usecase;

import businessrule.gateway.UserGatewayFactory;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.QuestionGateway;
import businessrule.gateway.UserGateway;
import entity.Question;
import entity.User;

/**
 * This class represents interacting for rating an answer to a question.
 */
public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final HomePageOutputBoundary homePageOutputBoundary;
    private final UserGatewayFactory userGatewayFactory;

    /**
     * Constructs a RateInteractor instance with the required dependencies.
     *
     * @param questionGateway         The gateway for accessing question-related data.
     * @param homePageOutputBoundary The output boundary for the homepage responses.
     * @param userGatewayFactory     The factory for creating UserGateways.
     */
    public RateInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public HomePageResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();
        int userId = rateRequestModel.getUserId();

        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.getUser(userId);

        Question answer = questionGateway.getQuestion(answerId);
        String userType;
        if (user.isQuestionRateable(answer)) {
            questionGateway.updateRating(answerId, rating);
            if (user.isClient()) {
                userType = "Client";
            } else {
                userType = "Attorney";
            }
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName(), userType);
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        } else {
            return homePageOutputBoundary.prepareFail("You cannot rate this question!");
        }
    }

}