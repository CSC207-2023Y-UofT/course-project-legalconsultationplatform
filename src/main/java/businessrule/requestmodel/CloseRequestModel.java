package businessrule.requestmodel;

public class CloseRequestModel {
    private final int questionId;

    public CloseRequestModel(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }
}
