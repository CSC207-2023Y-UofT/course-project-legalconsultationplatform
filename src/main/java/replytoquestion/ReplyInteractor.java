package replytoquestion;

import gateway.PostGateway;
import gateway.QuestionGateway;
import clientregister.RandomNumberGenerator;
import gateway.UserGateway;
import gateway.UserGatewayFactory;
import questionentities.Post;
import questionentities.Question;
import userentities.User;

import java.time.LocalDate;


public class ReplyInteractor implements PostInputBoundary{

    final QuestionGateway questionGateway;
    final PostGateway postGateway;
    final MessageOutputBoundary messageOutputBoundary;
    final PostFactory postFactory;
    final UserGatewayFactory userGatewayFactory;

    public ReplyInteractor(QuestionGateway questionGateway, PostGateway postGateway, MessageOutputBoundary messageOutputBoundary, PostFactory postFactory, UserGatewayFactory userGatewayFactory){
        this.questionGateway = questionGateway;
        this.postGateway = postGateway;
        this.messageOutputBoundary = messageOutputBoundary;
        this.postFactory = postFactory;
        this.userGatewayFactory = userGatewayFactory;
    }

    @Override
    public MessageResponseModel createPost(PostRequestModel postRequestModel) {
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
            return messageOutputBoundary.prepareSuccess("success");
        }
        else{
            return messageOutputBoundary.prepareFail("You are not allowed to send this reply.");
        }
    }
}
