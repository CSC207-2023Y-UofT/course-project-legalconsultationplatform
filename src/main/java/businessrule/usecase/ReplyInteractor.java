package businessrule.usecase;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.PostGateway;
import businessrule.gateway.QuestionGateway;
import businessrule.usecase.util.EmailNotificationSender;
import businessrule.usecase.util.RandomNumberGenerator;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import entity.Post;
import entity.Question;
import entity.factory.PostFactory;
import entity.User;

import java.time.LocalDate;


public class ReplyInteractor implements PostInputBoundary {

    final QuestionGateway questionGateway;
    final PostGateway postGateway;
    final HomePageOutputBoundary homePageOutputBoundary;
    final PostFactory postFactory;
    final UserGatewayFactory userGatewayFactory;

    public ReplyInteractor(QuestionGateway questionGateway, PostGateway postGateway, HomePageOutputBoundary homePageOutputBoundary, PostFactory postFactory, UserGatewayFactory userGatewayFactory) {
        this.questionGateway = questionGateway;
        this.postGateway = postGateway;
        this.homePageOutputBoundary = homePageOutputBoundary;
        this.postFactory = postFactory;
        this.userGatewayFactory = userGatewayFactory;
    }


    @Override
    public HomePageResponseModel createPost(PostRequestModel postRequestModel) {
        // get input data
        int userId = postRequestModel.getUserId();
        UserGateway<? extends User> userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);
        Question question = questionGateway.get(postRequestModel.getQuestionId());

        // handle reply logic and prepare response model
        boolean isQuestionReplyable = user.isQuestionReplyable(question);

        // handle reply logic and prepare response model
        String userType;
        if (isQuestionReplyable) {
            // if replyable, prepare post entity and update related field
            Post post = createPostEntity(postRequestModel);
            postGateway.save(post);
            questionGateway.updatePosts(postRequestModel.getQuestionId(), post);
            questionGateway.updateIsTaken(question.getQuestionId(), question.isTaken());
            questionGateway.updateTakenByAttorney(question.getQuestionId(), question.getTakenByAttorney());
            questionGateway.updateTakenAt(question.getQuestionId(), question.getTakenAt());
            userGateway.updateQuestionList(userId, question);
            if (user.isClient()){
                userType = "Client";
                if (question.isTaken()) {
                    try {
                        UserGateway attorneyGateway = userGatewayFactory.createUserGateway(question.getTakenByAttorney());
                        User attorney = attorneyGateway.get(question.getTakenByAttorney());
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
            } else{
                userType = "Attorney";
                try {
                    UserGateway clientGateway = userGatewayFactory.createUserGateway(userId);
                    User client = clientGateway.get(question.getTakenByAttorney());
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
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(user.getUserId(), user.getUserName(), userType);
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        }
        else{
            return homePageOutputBoundary.prepareFail("You cannot reply to this question");
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


    private Post createPostEntity(PostRequestModel RequestModel){
        // generate question id
        int randomPostId = generatePostId();

        // create question entity
        LocalDate now = LocalDate.now();
        return postFactory.create(randomPostId, RequestModel.getQuestionId(), now, RequestModel.getPostText(), RequestModel.getUserId());
    }
}
