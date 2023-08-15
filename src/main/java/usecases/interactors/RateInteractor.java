package usecases.interactors;

import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.gateway.*;
import usecases.inputboundary.RateInputBoundary;
import usecases.outputboundary.UserOutputBoundary;
import usecases.requests.RateRequestModel;
import usecases.gateway.QuestionGateway;
import usecases.responses.UserResponseModel;
import usecases.utils.EmailNotificationSender;
import entities.user.Attorney;
import entities.Question;

/**
 * This class represents the interactor responsible for rating an answer.
 * This interactor handles the process of rating an answer given by a client, updating the rating of the associated question.
 */
public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final UserOutputBoundary outputBoundary;
    private final AttorneyGateway attorneyGateway;

    /**
     * Constructor for RateInteractor.
     *
     * @param questionGateway The gateway for managing question entities.
     * @param outputBoundary The output boundary for preparing user response models.
     * @param attorneyGateway The gateway for managing attorney entities
     */
    public RateInteractor(QuestionGateway questionGateway, UserOutputBoundary outputBoundary, AttorneyGateway attorneyGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.attorneyGateway = attorneyGateway;
    }

    /**
     * Rate an answer and update the associated question's rating.
     *
     * @param rateRequestModel The request model containing rating details.
     * @return The response model for the home page.
     */
    @Override
    public UserResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        // get input
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        Question answer = questionGateway.get(answerId);


        if (answer.isClose() && answer.isTaken()) {
            questionGateway.updateRating(answerId, rating);
            answer.setRating(rating);
            Attorney attorney = attorneyGateway.get(answer.getTakenByAttorney());

            // Send email notification to the attorney
            sendEmail(attorney, answer);

            // prepare response
            return outputBoundary.prepareSuccess(response);
        } else {
            return outputBoundary.prepareFail("You cannot rate this question!");
        }
    }

    private void sendEmail(Attorney attorney, Question answer) {
        try {
            String attorneyEmail = attorney.getEmail();
            String title = "New Feedback to Your Answer";
            String attorneyName = attorney.getUserName();
            String questionType = answer.getType();
            String questionTitle = answer.getTitle();
            String platformName = "Legal Consultation Platform+";
            String stringRating = (answer.getRating() == 0) ? "Not satisfied" : "Satisfied";

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
    }
}
