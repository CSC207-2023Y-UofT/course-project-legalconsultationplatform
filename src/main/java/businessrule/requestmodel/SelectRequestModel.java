package businessrule.requestmodel;

/**
 * This class represents a request model for selecting a question.
 * It encapsulates the necessary information for selecting a question, including the question ID
 * and the user ID requesting the selection.
 */
public class SelectRequestModel {
    private final int questionId;
    private final int userId;

    /**
     * Constructs a SelectRequestModel with the provided question ID and user ID.
     *
     * @param questionId The ID of the question to be selected.
     * @param userId The ID of the user making the selection request.
     */
    public SelectRequestModel(int questionId, int userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    /**
     * Gets the question ID from the request model.
     *
     * @return The ID of the question.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Gets the user ID from the request model.
     *
     * @return The ID of the user making the selection request.
     */
    public int getUserId() {
        return userId;
    }
}