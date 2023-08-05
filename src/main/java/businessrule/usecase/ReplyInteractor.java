package businessrule.usecase;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import businessrule.gateway.PostGateway;
import businessrule.gateway.QuestionGateway;
import entity.RandomNumberGenerator;
import businessrule.gateway.UserGateway;
import businessrule.gateway.UserGatewayFactory;
import entity.Post;
import entity.Question;
import entity.PostFactory;
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
        User user = userGateway.getUser(userId);
        Question question = questionGateway.getQuestion(postRequestModel.getQuestionId());

        // generate post id
        RandomNumberGenerator generator = new RandomNumberGenerator();
        int randomPostId = generator.generatePostId(9);
        boolean exist = postGateway.existsById(randomPostId);
        while (exist) {
            randomPostId = generator.generatePostId(9);
            exist = postGateway.existsById(randomPostId);
        }

        // prepare the post entity
        LocalDate now = LocalDate.now();
        boolean isQuestionReplyable = user.isQuestionReplyable(question);

        // handle reply logic and prepare response model
        String userType;
        if (isQuestionReplyable) {
            // if replyable, prepare post entity and update related field
            Post post = postFactory.create(randomPostId, postRequestModel.getQuestionId(), now, postRequestModel.getPostText(), postRequestModel.getUserId());
            questionGateway.updatePosts(postRequestModel.getQuestionId(), post);
            postGateway.savePost(post);
            questionGateway.updateIsTaken(question.getQuestionId(), question.isTaken());
            questionGateway.updateTakenByAttorney(question.getQuestionId(), question.getTakenByAttorney());
            questionGateway.updateTakenAt(question.getQuestionId(), question.getTakenAt());
            userGateway.updateQuestionList(userId, question);
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(user.getUserId(), user.getUserName());
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
}
