package businessrule.usecase;

import businessrule.gateway.*;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.usecase.util.EmailNotificationSender;
import entity.Attorney;
import entity.Client;
import entity.Question;

/**
 * This class represents the interactor responsible for rating an answer.
 *
 * This interactor handles the process of rating an answer given by a client, updating the rating of the associated question.
 */
public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final HomePageOutputBoundary homePageOutputBoundary;
    private final ClientGateway clientGateway;
    private final AttorneyGateway attorneyGateway;

    public RateInteractor(QuestionGateway questionGateway, HomePageOutputBoundary homePageOutputBoundary, ClientGateway clientGateway, AttorneyGateway attorneyGateway) {
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
        this.attorneyGateway = attorneyGateway;
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

        Client user = clientGateway.get(userId);
        Question answer = questionGateway.get(answerId);

        if (answer.isClose() && answer.isTaken()) {
            String userType = "Client";
            questionGateway.updateRating(answerId, rating);
            // Send email notification to the attorney
            try {
            Attorney attorney = attorneyGateway.get(answer.getTakenByAttorney());
            String attorneyEmail = attorney.getEmail();
            String title = "New Feedback to Your Answer";
            String attorneyName = attorney.getUserName();
            String questionType = answer.getType();
            String questionTitle = answer.getTitle();
            String platformName = "Legal Consultation Platform+"; // Replace if you have a different platform name
            String stringRating = "satisfied"; // This should be dynamically set based on the actual rating (either "satisfied" or "unsatisfied")

            String emailContent = "Dear " + attorneyName + ",\n\n" +
                    "We hope you're doing well. We wanted to inform you that your response to the " + questionType +
                    " query titled \"" + questionTitle + "\" has received feedback from the client. They've expressed that they are " + stringRating + " with your answer.\n\n" +
                    "Feedback is crucial for our continuous improvement, and we appreciate your dedication to providing thorough and knowledgeable responses. " +
                    "You can log in to your attorney dashboard on " + platformName + " to view more details on the feedback.\n\n" +
                    "Warm regards,\n\n" +
                    "Team, " + platformName + "\n";


                EmailNotificationSender.sendEmail(attorneyEmail, title, emailContent);
            } catch (Exception e){
                e.printStackTrace();
            }
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(userId, user.getUserName(), userType);
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        } else {
            return homePageOutputBoundary.prepareFail("You cannot rate this question!");
        }
    }
}
