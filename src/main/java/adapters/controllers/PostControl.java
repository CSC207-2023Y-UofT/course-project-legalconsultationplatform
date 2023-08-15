package adapters.controllers;

import usecases.inputboundary.PostInputBoundary;
import usecases.requests.PostRequestModel;
import usecases.responses.TheQuestionResponseModel;

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
