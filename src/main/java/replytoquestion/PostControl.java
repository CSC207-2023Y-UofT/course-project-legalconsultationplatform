package replytoquestion;

import presenter.MessageResponseModel;

import java.time.LocalDate;

public class PostControl {
    final PostInputBoundary postInput;

    public PostControl(PostInputBoundary postInput){
        this.postInput = postInput;
    }

    MessageResponseModel createPost(int questionId, int userId, String postText){
        PostRequestModel postRequestModel = new PostRequestModel(questionId, userId, postText);

        return postInput.createPost(postRequestModel);
    }
}
