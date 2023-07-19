package userselectquestion;

public class SelectRequestModel {
    private int questionId;
    private int userId;

    public SelectRequestModel(int questionId, int userId) {
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
