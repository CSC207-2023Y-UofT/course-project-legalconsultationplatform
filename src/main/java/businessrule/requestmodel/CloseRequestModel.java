package businessrule.requestmodel;

/**
 * This class represents a request model to close a question made by a client.
 */
public class CloseRequestModel {
    private final int questionId;
    private final int userId;

    /**
     * Constructs a CloseRequestModel object with the provided question ID and user ID.
     *
     * @param questionId The ID of the question to be closed.
     * @param userId The ID of the user making the close request.
     */
    public CloseRequestModel(int questionId, int userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    /**
     * Gets the ID of the question to be closed.
     *
     * @return The ID of the question.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Gets the ID of the user making the close request.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }
}