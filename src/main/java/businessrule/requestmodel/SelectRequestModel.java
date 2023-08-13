package businessrule.requestmodel;

public class SelectRequestModel {
    private final int questionId;

    public SelectRequestModel(int questionId) {
        this.questionId = questionId;
    }

    public int getQuestionId() {
        return questionId;
    }
}
