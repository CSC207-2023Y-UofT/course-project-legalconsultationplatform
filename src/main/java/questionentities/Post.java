package questionentities;

import java.time.LocalDate;

public class Post {
    private final int questionId;
    private final LocalDate createAt;
    private final String postText;


    public Post(int questionId, LocalDate createAt, String postText){
        this.questionId = questionId;
        this.createAt = createAt;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public String getPostText() {
        return postText;
    }
}
