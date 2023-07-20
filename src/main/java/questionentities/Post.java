package questionentities;

import java.time.LocalDate;

public class Post {
    private final int postId;
    private final int questionId;
    private final LocalDate createAt;
    private final String postText;


    public Post(int postId, int questionId, LocalDate createAt, String postText){
        this.postId = postId;
        this.questionId = questionId;
        this.createAt = createAt;
        this.postText = postText;
    }

    public int getPostId() {
        return postId;
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
