package replytoquestion;

import gateway.PostGateway;
import gateway.QuestionGateway;
import clientregister.RandomNumberGenerator;
import gateway.UserGateway;
import gateway.UserGatewayFactory;
import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import questionentities.Post;
import questionentities.Question;
import userentities.User;

import java.time.LocalDate;
import java.util.Random;


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
            return messageOutputBoundary.prepareSuccess("succeed");
        }
        else{
            return messageOutputBoundary.prepareFail("fail");
        }
    }
}
