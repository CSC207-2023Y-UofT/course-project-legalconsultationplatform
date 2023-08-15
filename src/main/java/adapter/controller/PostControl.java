package adapter.controller;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;

/**
 * This class represents the controller responsible for handling interactions related to creating posts.
 */
public class PostControl {
    private final PostInputBoundary postInput;

    /**
     * Constructs a new PostControl instance.
     *
     * @param postInput The input boundary for post-related operations.
     */
    public PostControl(PostInputBoundary postInput){
        this.postInput = postInput;
    }

    /**
     * Creates a new post and associates it with a question.
     *
     * @param questionId The ID of the question the post is associated with.
     * @param postText The content of the post.
     * @return A TheQuestionResponseModel containing information about the updated question UI.
     */
    public TheQuestionResponseModel createPost(int questionId, String postText){
        PostRequestModel postRequestModel = new PostRequestModel(questionId, postText);

        return postInput.createPost(postRequestModel);
    }
}
