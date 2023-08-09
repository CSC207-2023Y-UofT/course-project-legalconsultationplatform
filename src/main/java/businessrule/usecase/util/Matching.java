package businessrule.usecase.util;

/**
 * This class represents a matching between a question and an attorney.
 * It stores information about the question ID and the attorney ID that are matched.
 */
public class Matching {

    private int matchingId;
    private int questionId;
    private int attorneyId;

    /**
     * Constructs a new `Matching` object with the specified question ID and attorney ID.
     *
     * @param questionId The ID of the question to be matched.
     * @param attorneyId The ID of the attorney to be matched.
     */
    public Matching(int questionId, int attorneyId) {
        this.questionId = questionId;
        this.attorneyId = attorneyId;
    }

    /**
     * Gets the ID of the matched question.
     *
     * @return The ID of the question.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Sets the ID of the matched question.
     *
     * @param questionId The ID of the question to set.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Gets the ID of the matched attorney.
     *
     * @return The ID of the attorney.
     */
    public int getAttorneyId() {
        return attorneyId;
    }

    /**
     * Sets the ID of the matched attorney.
     *
     * @param attorneyId The ID of the attorney to set.
     */
    public void setAttorneyId(int attorneyId) {
        this.attorneyId = attorneyId;
    }
}