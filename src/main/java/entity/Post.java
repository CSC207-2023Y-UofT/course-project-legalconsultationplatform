package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This is a class representing a post in a question thread.
 *
 * A "Post" is a message or response posted in the context of a specific question thread.
 * Each post contains information such as the ID of the associated question, the post ID,
 * the creation date, the text of the post, and an identifier indicating which user the post
 * belongs to.
 */

@Entity
public class Post {
    @JsonProperty(required = true)
    private int questionId;
    @Id
    @JsonProperty(required = true)
    private int postId;
    @JsonProperty(required = true)
    private LocalDate createAt;
    @JsonProperty(required = true)
    private String postText;
    @JsonProperty(required = true)
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

    @Override
    public int hashCode() {return Objects.hashCode(postId);}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post)) return false;
        Post otherPost = (Post) obj;
        return postId == otherPost.postId;
    }

    @Override
    public String toString() {
        return String.format("This is a post in %d belongs to %d", questionId, belongsTo);
    }
}
