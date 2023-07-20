package replytoquestion;

import presenter.MessageResponseModel;

import java.time.LocalDate;

public class PostControl {
    final PostInputBoundary postInput;

    public PostControl(PostInputBoundary postInput){
        this.postInput = postInput;
    }

    MessageResponseModel createPost(int questionId, LocalDate createAt, String postText){
        PostRequestModel postRequestModel = new PostRequestModel(questionId, createAt, postText);

        return postInput.createPost(postRequestModel);
    }

}