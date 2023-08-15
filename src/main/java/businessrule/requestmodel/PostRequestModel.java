package businessrule.requestmodel;

/**
 * This class represents a post request model for creating a new post.
 */
public class PostRequestModel {
    private final int questionId;
    private final String postText;

    /**
     * Constructs a new PostRequestModel instance.
     *
     * @param questionId The ID of the question associated with the post.
     * @param postText The text content of the post.
     */
    public PostRequestModel(int questionId, String postText){
        this.questionId = questionId;
        this.postText = postText;
    }

    /**
     * Get the ID of the question associated with the post.
     *
     * @return The question ID.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Get the text content of the post.
     *
     * @return The post text.
     */
    public String getPostText() {
        return postText;
    }
}
