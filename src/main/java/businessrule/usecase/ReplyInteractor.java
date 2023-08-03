package businessrule.usecase;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;
import driver.database.PostGateway;
import driver.database.QuestionGateway;
import entity.RandomNumberGenerator;
import driver.database.UserGateway;
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
        RandomNumberGenerator randomNumberGenerator = new RandomNumberGenerator();
        LocalDate now = LocalDate.now();

        UserGateway userGateway = userGatewayFactory.createUserGateway(postRequestModel.getUserId());
        User user = userGateway.getUser(postRequestModel.getUserId());
        Question question = questionGateway.getQuestion(postRequestModel.getQuestionId());
        boolean isQuestionReplyable = user.isQuestionReplyable(question);
        Post post = postFactory.create(randomNumberGenerator.generatePostId(9), postRequestModel.getQuestionId(), now, postRequestModel.getPostText(), postRequestModel.getUserId());
        questionGateway.updatePosts(postRequestModel.getQuestionId(), post);
        postGateway.savePost(post);
        if (isQuestionReplyable) {
            questionGateway.updateIsTaken(question.getQuestionId(), question.isTaken());
            questionGateway.updateTakenByAttorney(question.getQuestionId(), question.getTakenByAttorney());
            questionGateway.updateTakenAt(question.getQuestionId(), question.getTakenAt());
            HomePageResponseModel homePageResponseModel = new HomePageResponseModel(user.getUserId(), user.getUserName());
            return homePageOutputBoundary.prepareSuccess(homePageResponseModel);
        }
        else{
            return homePageOutputBoundary.prepareFail("You cannot reply to this question");
        }
    }
}
