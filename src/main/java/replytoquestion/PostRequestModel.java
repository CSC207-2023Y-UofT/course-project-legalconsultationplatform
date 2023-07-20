package replytoquestion;

import java.time.LocalDate;

public class PostRequestModel {
    private int questionId;
    private LocalDate createAt;
    private String postText;

    public PostRequestModel(int questionId, LocalDate createAt, String postText){
        this.questionId = questionId;
        this.createAt = createAt;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    public String getPostText() {
        return postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }
}
