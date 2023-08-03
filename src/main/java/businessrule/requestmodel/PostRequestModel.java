package businessrule.requestmodel;

import java.time.LocalDate;

public class PostRequestModel {
    private final int questionId;
    private final int userId;
    private final String postText;

    public PostRequestModel(int questionId, int userId, String postText){
        this.questionId = questionId;
        this.userId = userId;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getUserId() {
        return userId;
    }

    public String getPostText() {
        return postText;
    }
}
