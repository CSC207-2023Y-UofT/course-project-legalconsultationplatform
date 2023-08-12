package businessrule.usecase;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.PostGateway;
import businessrule.gateway.QuestionGateway;
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
        UserGateway userGateway = userGatewayFactory.createUserGateway(userId);
        User user = userGateway.get(userId);
        Question question = questionGateway.get(postRequestModel.getQuestionId());

        // handle reply logic and prepare response model
        boolean isQuestionReplyable = user.isQuestionReplyable(question);

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
            } else{
                userType = "Attorney";
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
