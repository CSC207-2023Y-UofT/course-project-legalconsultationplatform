package businessrule.usecase.util;

/**
 * This class represents a matching between a question and an attorney.
 * It stores information about the question ID and the attorney ID that are matched.
 */
public class Matching {
    private final int questionId;
    private final int attorneyId;

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

    public int getQuestionId() {
        return questionId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }
}
