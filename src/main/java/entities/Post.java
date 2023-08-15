package entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.Objects;

/**
 * This class represents a post in a question thread.
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

    /**
     * Constructs a new Post instance.
     *
     * @param questionId The ID of the associated question.
     * @param postId The ID of the post.
     * @param createAt The creation date of the post.
     * @param postText The text of the post.
     * @param belongsTo The identifier of the user to whom the post belongs.
     */
    public Post(int questionId, int postId, LocalDate createAt, String postText, int belongsTo){
        this.questionId = questionId;
        this.postId = postId;
        this.createAt = createAt;
        this.postText = postText;
        this.belongsTo = belongsTo;
    }

    /**
     * Gets the ID of the associated question.
     *
     * @return The question ID.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Gets the ID of the post.
     *
     * @return The post ID.
     */
    public int getPostId() {
        return postId;
    }

    /**
     * Gets the creation date of the post.
     *
     * @return The creation date.
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * Gets the text of the post.
     *
     * @return The post text.
     */
    public String getPostText() {
        return postText;
    }

    /**
     * Gets the identifier of the user to whom the post belongs.
     *
     * @return The user identifier.
     */
    public int getBelongsTo() {return belongsTo;}

    /**
     * Sets the ID of the associated question.
     *
     * @param questionId The question ID to set.
     */
    public void setQuestionId(int questionId) {this.questionId = questionId;}

    /**
     * Sets the ID of the post.
     *
     * @param postId The post ID to set.
     */
    public void setPostId(int postId) {this.postId = postId;}

    /**
     * Sets the creation date of the post.
     *
     * @param createAt The creation date to set.
     */
    public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

    /**
     * Sets the text of the post.
     *
     * @param postText The post text to set.
     */
    public void setPostText(String postText) {this.postText = postText;}

    /**
     * Sets the identifier of the user to whom the post belongs.
     *
     * @param belongsTo The user identifier to set.
     */
    public void setBelongsTo(int belongsTo) {this.belongsTo = belongsTo;}

    /**
     * {@inheritDoc}
     */
    @Override
    public int hashCode() {return Objects.hashCode(postId);}

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Post)) return false;
        Post otherPost = (Post) obj;
        return postId == otherPost.postId;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String toString() {
        return String.format("This is a post in %d belongs to %d", questionId, belongsTo);
    }
}