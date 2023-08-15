package businessrule.requestmodel;

/**
 * This class represents a request model to close a question made by a client.
 */
public class CloseRequestModel {
    private final int questionId;

    /**
     * Constructs a CloseRequestModel object with the provided question ID and user ID.
     *
     * @param questionId The ID of the question to be closed.
     */
    public CloseRequestModel(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the ID of the question to be closed.
     *
     * @return The ID of the question.
     */
    public int getQuestionId() {
        return questionId;
    }
}
