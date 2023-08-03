package businessrule.requestmodel;

import java.time.LocalDate;

public class PostRequestModel {
    private int questionId;
    private int userId;
    private String postText;

    public PostRequestModel(int questionId, int userId, String postText){
        this.questionId = questionId;
        this.userId = userId;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
