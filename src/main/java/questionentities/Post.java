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
    private int belongsTo;

    public Post() {
    }

    public Post(int questionId, int postId, LocalDate createAt, String postText, int belongsTo){
        this.questionId = questionId;
        this.postId = postId;
        this.createAt = createAt;
        this.postText = postText;
        this.belongsTo = belongsTo;
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

    public int getBelongsTo() {return belongsTo;}

    public void setQuestionId(int questionId) {this.questionId = questionId;}

    public void setPostId(int postId) {this.postId = postId;}

    public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

    public void setPostText(String postText) {this.postText = postText;}

    public void setBelongsTo(int belongsTo) {this.belongsTo = belongsTo;}
}
