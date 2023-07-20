package replytoquestion;

import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import questiongateway.QuestionGateway;

public class ReplyInteractor implements PostInputBoundary{

    final QuestionGateway questionGateway;
    final MessageOutputBoundary messageOutputBoundary;
    final PostFactory postFactory;

    public ReplyInteractor(QuestionGateway questionGateway, MessageOutputBoundary messageOutputBoundary, PostFactory postFactory){
        this.questionGateway = questionGateway;
        this.messageOutputBoundary = messageOutputBoundary;
        this.postFactory = postFactory;
    }
    @Override
    public MessageResponseModel createPost(PostRequestModel postRequestModel) {
        postRequestModel.getQuestionId();
        return null;
    }
}
