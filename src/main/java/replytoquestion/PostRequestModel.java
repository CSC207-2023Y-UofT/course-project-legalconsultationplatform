package replytoquestion;

import java.time.LocalDate;

public class PostRequestModel {
    private int questionId;
    private String postText;

    public PostRequestModel(int questionId, String postText){
        this.questionId = questionId;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
