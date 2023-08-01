package userclosequestion;

public class CloseRequestModel {
    private int questionId;
    private int userId;

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

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
