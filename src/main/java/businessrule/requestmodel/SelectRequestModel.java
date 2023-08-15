package businessrule.requestmodel;

/**
 * This class represents a request model for selecting a question.
 * It encapsulates the necessary information for selecting a question, including the question ID
 * and the user ID requesting the selection.
 */
public class SelectRequestModel {
    private final int questionId;

    /**
     * Constructs a SelectRequestModel with the provided question ID and user ID.
     *
     * @param questionId The ID of the question to be selected.
     */
    public SelectRequestModel(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the question ID from the request model.
     *
     * @return The ID of the question.
     */
    public int getQuestionId() {
        return questionId;
    }
}
