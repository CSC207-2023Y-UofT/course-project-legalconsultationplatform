package replytoquestion;

import gateway.PostGateway;
import gateway.QuestionGateway;
import gateway.UserGateway;
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
    final UserGateway userGateway;

    public ReplyInteractor(QuestionGateway questionGateway, PostGateway postGateway, MessageOutputBoundary messageOutputBoundary, PostFactory postFactory, UserGateway userGateway){
        this.questionGateway = questionGateway;
        this.postGateway = postGateway;
        this.messageOutputBoundary = messageOutputBoundary;
        this.postFactory = postFactory;
        this.userGateway = userGateway;
    }

    public int getRandomId(){
        Random rand = new Random();
        int upperbound = 10000000;
        int int_random = rand.nextInt(upperbound);
        boolean ifExists = postGateway.checkExistsById(int_random);
        while (ifExists) {
            int_random = rand.nextInt(upperbound);
            ifExists = postGateway.checkExistsById(int_random);
        }
        return int_random;
    }

    @Override
    public MessageResponseModel createPost(PostRequestModel postRequestModel) {
        LocalDate now = LocalDate.now();
        User user = userGateway.getUser(postRequestModel.getUserId());
        Question question = questionGateway.getQuestion(postRequestModel.getQuestionId());
        boolean isQuestionReplyable = user.isQuestionReplyable(question);
        Post post = postFactory.create(getRandomId(), postRequestModel.getQuestionId(), now, postRequestModel.getPostText(), postRequestModel.getUserId();
        questionGateway.updatePosts(postRequestModel.getQuestionId(), post);
        postGateway.savePost(post);
        MessageResponseModel messageResponseModel = new MessageResponseModel(); // 需要后续补充
        String message = new String("success");
        if (isQuestionReplyable) {
            return messageOutputBoundary.prepareSuccess(message);
        }
        else{
            return messageOutputBoundary.prepareFail("You are not allowed to send this reply.");
        }
    }
}
