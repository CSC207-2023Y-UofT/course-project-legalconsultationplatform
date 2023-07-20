package replytoquestion;

import gateway.PostGateway;
import gateway.QuestionGateway;
import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import questionentities.Post;

import java.time.LocalDate;
import java.util.Random;


public class ReplyInteractor implements PostInputBoundary{

    final QuestionGateway questionGateway;
    final PostGateway postGateway;
    final MessageOutputBoundary messageOutputBoundary;
    final PostFactory postFactory;

    public ReplyInteractor(QuestionGateway questionGateway, MessageOutputBoundary messageOutputBoundary, PostFactory postFactory){
        this.questionGateway = questionGateway;
        this.messageOutputBoundary = messageOutputBoundary;
        this.postFactory = postFactory;
    }
    @Override
    public MessageResponseModel createPost(PostRequestModel postRequestModel) {
        LocalDate now = LocalDate.now();
        Random rand = new Random();
        int upperbound = 10000000;
        int int_random = rand.nextInt(upperbound);
        boolean ifExists = postGateway.checkExistsByName(int_random);
        while (ifExists) {
            int_random = rand.nextInt(upperbound);
            ifExists = questionGateway.checkExistsByName(int_random);
        }
        Post post = postFactory.create(postRequestModel.getQuestionId(), now, postRequestModel.getPostText());
        questionGateway.updatePosts(postRequestModel.getQuestionId(), post);

        return null;
    }
}
