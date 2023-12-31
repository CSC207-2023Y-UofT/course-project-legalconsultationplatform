package usecases.interactors;

import entities.user.Attorney;
import entities.user.User;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.dto.PostDisplay;
import usecases.inputboundary.PostInputBoundary;
import usecases.outputboundary.TheQuestionOutputBoundary;
import usecases.requests.PostRequestModel;
import usecases.gateway.PostGateway;
import usecases.gateway.QuestionGateway;
import usecases.responses.TheQuestionResponseModel;
import usecases.responses.UserResponseModel;
import usecases.gateway.UserGateway;
import infrastructure.database.UserGatewayFactory;
import entities.*;
import entities.factories.PostFactory;
import usecases.utils.*;

import java.time.LocalDate;
import java.util.Map;

/**
 * This class represents the use case for creating a new post (reply) in response to a question.
 */
public class ReplyInteractor implements PostInputBoundary {

    final QuestionGateway questionGateway;
    final PostGateway postGateway;
    final TheQuestionOutputBoundary outputBoundary;
    final PostFactory postFactory;
    final UserGatewayFactory userGatewayFactory;
    final static String EMPTY_CONTENT = "Type your content here...";

    public ReplyInteractor(QuestionGateway questionGateway, PostGateway postGateway, TheQuestionOutputBoundary outputBoundary, PostFactory postFactory, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.postGateway = postGateway;
        this.outputBoundary = outputBoundary;
        this.postFactory = postFactory;
        this.userGatewayFactory = userGatewayFactory;
    }

    /**
     * Creates a new post (reply) in response to a question.
     *
     * @param postRequestModel The request model containing the details for the new post.
     * @return A response model containing information about the updating question UI.
     */
    @Override
    public TheQuestionResponseModel createPost(PostRequestModel postRequestModel) {
        // get input data
        UserSession session = SessionManager.getSession();
        UserResponseModel response = session.getUserResponseModel();
        int userId = response.getUserId();
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);
        Question question = questionGateway.get(postRequestModel.getQuestionId());

        // check empty content
        if (postRequestModel.getPostText().equals(EMPTY_CONTENT)) {
            outputBoundary.prepareFail("Please specify your reply content.");
        }
        // handle reply logic and prepare response model
        boolean isQuestionReplyable = user.isQuestionReplyable(question);

        if (isQuestionReplyable) {
            // if replyable, prepare post entity and update related field
            Post post = createPostEntity(postRequestModel, response.getUserId());
            postGateway.save(post);
            question.addPosts(post);
            questionGateway.updatePosts(postRequestModel.getQuestionId(), post);
            questionGateway.updateIsTaken(question.getQuestionId(), question.isTaken());
            questionGateway.updateTakenByAttorney(question.getQuestionId(), question.getTakenByAttorney());
            questionGateway.updateTakenAt(question.getQuestionId(), question.getTakenAt());

            userGateway.updateQuestionList(userId, question);
            if (response.getUserType().equals("Client")){
                if (question.isTaken()) {
                    sendAttorneyEmail(question);
                }
            } else{
                sendClientEmail(question);
            }

            PostMapConstructor postMapConstructor = new PostMapConstructor(userGatewayFactory);
            Map<Integer, PostDisplay> postMap = postMapConstructor.constructPostMap(question);
            TheQuestionResponseModel theQuestionResponseModel = BuilderService.getInstance().constructTheQuestionResponse(question, response, postMap);
            return outputBoundary.prepareSuccess(theQuestionResponseModel);
        }
        else{
            return outputBoundary.prepareFail("You cannot reply to this question");
        }
    }

    private void sendAttorneyEmail(Question question) {
        try {
            UserGateway<? extends User> attorneyGateway = userGatewayFactory.createUserGateway(question.getTakenByAttorney());
            Attorney attorney = (Attorney) attorneyGateway.get(question.getTakenByAttorney());

            // Send email notification to the attorney
            String title = "New Reply to Your Answer";
            String attorneyEmail = attorney.getEmail();
            String attorneyName = attorney.getUserName();
            String questionType = question.getType();
            String questionTitle = question.getTitle();
            String platformName = "Legal Consultation Platform+";

            String emailContent = "Dear " + attorneyName + ",\n\n" +
                    "We hope this message finds you well. We're reaching out to inform you that there has been a follow-up to your response regarding the " + questionType +
                    " query titled \"" + questionTitle + "\".\n\n" +
                    "We kindly request you to log in to your attorney dashboard on " + platformName + " to address the additional questions or clarifications sought by the client.\n\n" +
                    "Your expertise and timely response are highly valued by both our team and the clients we serve.\n\n" +
                    "Warm regards,\n\n" +
                    "Team, " + platformName + "\n";

            EmailNotificationSender.sendEmail(attorneyEmail, title, emailContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private void sendClientEmail(Question question) {
        try {
            UserGateway<? extends User> clientGateway = userGatewayFactory.createUserGateway(question.getAskedByClient());
            User client = clientGateway.get(question.getAskedByClient());
            // Send email notification to the client
            String clientEmail = client.getEmail();
            String title = "New Reply to Your Question";
            String clientName = client.getUserName();
            String questionType = question.getType();
            String questionTitle = question.getTitle();
            String platformName = "Legal Consultation Platform+";

            String emailContent = "Dear " + clientName + ",\n\n" +
                    "I hope this message finds you well. We are pleased to inform you that we have addressed your recent " + questionType +
                    " query titled \"" + questionTitle + "\".\n\n" +
                    "To view the detailed response, please log in to your personal account and click on the \"Question & Response History\" button. " +
                    "There, you'll find the mentioned query and our corresponding reply.\n\n" +
                    "Should you have any follow-up questions or require further clarification, please don't hesitate to reach out. " +
                    "Your satisfaction and understanding are of utmost importance to us.\n\n" +
                    "Warm regards,\n\n" +
                    "Team, " + platformName + "\n";

            EmailNotificationSender.sendEmail(clientEmail, title, emailContent);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    private int generatePostId(){
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomPostId = generator.generatePostId(9);
        boolean exists = postGateway.existsById(randomPostId);
        while (exists){
            randomPostId = generator.generatePostId(9);
            exists = postGateway.existsById(randomPostId);
        }
        return randomPostId;
    }


    private Post createPostEntity(PostRequestModel RequestModel, int userId){
        // generate question id
        int randomPostId = generatePostId();

        // create question entity
        LocalDate now = LocalDate.now();
        return postFactory.create(randomPostId, RequestModel.getQuestionId(), now, RequestModel.getPostText(), userId);
    }
}
