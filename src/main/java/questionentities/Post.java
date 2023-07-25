package questionentities;

import java.time.LocalDate;

public class Post {
    private final int questionId;
    private final int postId;
    private final LocalDate createAt;
    private final String postText;


    public Post(int questionId, int postId, LocalDate createAt, String postText){
        this.questionId = questionId;
        this.postId = postId;
        this.createAt = createAt;
        this.postText = postText;
    }

    public int getQuestionId() {
        return questionId;
    }

    public int getPostId() {
        return postId;
    }

    public LocalDate getCreateAt() {
        return createAt;
    }

    public String getPostText() {
        return postText;
    }
}
