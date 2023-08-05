package adapter.controller;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public class PostControl {
    private final PostInputBoundary postInput;

    public PostControl(PostInputBoundary postInput){
        this.postInput = postInput;
    }

    public HomePageResponseModel createPost(int questionId, int userId, String postText){
        PostRequestModel postRequestModel = new PostRequestModel(questionId, userId, postText);

        return postInput.createPost(postRequestModel);
    }
}
