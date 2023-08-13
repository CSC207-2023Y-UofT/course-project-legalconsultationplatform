package businessrule.usecase.util;

public class Matching {
    private final int questionId;
    private final int attorneyId;

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
