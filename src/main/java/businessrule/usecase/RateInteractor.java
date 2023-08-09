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

/**
 * This class represents the interactor responsible for rating an answer.
 *
 * This interactor handles the process of rating an answer given by a client, updating the rating of the associated question.
 */
public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final HomePageOutputBoundary homePageOutputBoundary;
    private final ClientGateway clientGateway;

    /**
     * Constructor for RateInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param homePageOutputBoundary The output boundary for preparing home page response models.
     * @param clientGateway The gateway for managing client entities.
     */
    public RateInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, ClientGateway clientGateway) {
        this.questionGateway = questionGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.clientGateway = clientGateway;
    }

    /**
     * Rate an answer and update the associated question's rating.
     *
     * @param rateRequestModel The request model containing rating details.
     * @return The response model for the home page.
     */
    @Override
    public HomePageResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();
        int userId = rateRequestModel.getUserId();

        Client user = (Client) clientGateway.get(userId);
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