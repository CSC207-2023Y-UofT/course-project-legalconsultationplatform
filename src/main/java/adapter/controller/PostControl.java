package adapter.controller;

import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

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
    public PostControl(PostInputBoundary postInput) {
        this.postInput = postInput;
    }

    /**
     * Creates a new post and associates it with a question.
     *
     * @param questionId The ID of the question the post is associated with.
     * @param userId The ID of the user creating the post.
     * @param postText The content of the post.
     * @return A HomePageResponseModel containing information about the updated home page.
     */
    public HomePageResponseModel createPost(int questionId, int userId, String postText) {
        PostRequestModel postRequestModel = new PostRequestModel(questionId, userId, postText);

        return postInput.createPost(postRequestModel);
    }
}