package attorneygetrecommendation;

public class Matching {

    private int matchingId;
    private int questionId;
    private int attorneyId;

    public Matching(int questionId, int attorneyId) {
        this.questionId = questionId;
        this.attorneyId = attorneyId;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(int attorneyId) {
        this.attorneyId = attorneyId;
    }
}
