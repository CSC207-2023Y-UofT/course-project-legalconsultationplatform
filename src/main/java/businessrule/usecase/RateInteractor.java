package businessrule.usecase;

import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.gateway.ClientGateway;
import businessrule.gateway.*;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.outputboundary.UserOutputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.gateway.QuestionGateway;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.EmailNotificationSender;
import entity.Attorney;
import entity.Client;
import entity.Question;

public class RateInteractor implements RateInputBoundary {

    private final QuestionGateway questionGateway;
    private final UserOutputBoundary outputBoundary;
    private final ClientGateway clientGateway;
    private final AttorneyGateway attorneyGateway;

    public RateInteractor(QuestionGateway questionGateway, UserOutputBoundary outputBoundary, ClientGateway clientGateway, AttorneyGateway attorneyGateway) {
        this.questionGateway = questionGateway;
        this.outputBoundary = outputBoundary;
        this.clientGateway = clientGateway;
        this.attorneyGateway = attorneyGateway;
    }

    @Override
    public UserResponseModel rateAnswer(RateRequestModel rateRequestModel) {
        // get input
        int rating = rateRequestModel.getRating();
        int answerId = rateRequestModel.getAnswerId();
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        Client client = clientGateway.get(response.getUserId());
        Question answer = questionGateway.get(answerId);


        if (answer.isClose() && answer.isTaken()) {
            questionGateway.updateRating(answerId, rating);
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
            String stringRating = (answer.getRating() == 0) ? "Unsatisfied" : "Satisfied";

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
