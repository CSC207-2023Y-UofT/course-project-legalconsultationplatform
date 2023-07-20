package replytoquestion;

import presenter.MessageOutputBoundary;
import presenter.MessageResponseModel;
import questiongateway.QuestionGateway;

public class ReplyInteractor implements PostInputBoundary{

    final QuestionGateway questionGateway;
    final MessageOutputBoundary messageOutputBoundary;
    final PostFactory postFactory;

    @Override
    public MessageResponseModel createPost(PostRequestModel postRequestModel) {
        return null;
    }
}
