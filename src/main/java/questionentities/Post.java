package questionentities;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
public class Post {
    private int questionId;
    @Id
    private int postId;
    private LocalDate createAt;
    private String postText;

    public Post() {
    }

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
