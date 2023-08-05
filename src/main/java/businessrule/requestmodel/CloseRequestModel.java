package businessrule.requestmodel;
/**
 * This class represents a request model to close a question made by a client.
 */
public class CloseRequestModel {
    private final int questionId;
    private final int userId;

    public CloseRequestModel(int questionId, int userId) {
        this.questionId = questionId;
        this.userId = userId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUserId() {
        return userId;
    }
}
