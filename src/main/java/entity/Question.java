package entity;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This is a class representing a question thread.
 *
 * Each question thread contain information about the type of the question, the client who asked the question,
 * whether the question has been taken and who took the question, and a list of post belongs to the question.
 */
@Entity
public class Question {
    @Id
    @JsonProperty(required = true)
    private int questionId;
    @JsonProperty(required = true)
    private String type;
    private String title;
    @JsonProperty(required = true)
    private LocalDate createAt;
    @JsonProperty(required = true)
    private int askedByClient;
    @JsonProperty(required = true)
    private LocalDate legalDeadline;
    @JsonProperty(value = "isTaken", required = true)
    private boolean isTaken;
    @JsonProperty(required = true)
    private int takenByAttorney;
    @JsonProperty(required = true)
    private LocalDate takenAt;
    @JsonProperty(value = "isClose", required = true)
    private boolean isClose;
    @JsonProperty(required = true)
    private int rating;
    @OneToMany(targetEntity = Post.class, fetch = FetchType.EAGER)
    @JsonProperty(required = true)
    private List<Post> posts;
    public static final int MISSING_RATING = -1;


    public Question() {
        rating = MISSING_RATING;
        posts = new ArrayList<Post>();
    }

    public Question(int questionId, String type, String title, LocalDate createAt, int askedByClient, LocalDate legalDeadline) {
        this.questionId = questionId;
        this.type = type;
        this.title = title;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.legalDeadline = legalDeadline;
        this.rating = MISSING_RATING;
        this.posts = new ArrayList<Post>();
    }

    public int getQuestionId() {return questionId;}

    public String getType() {return type;}

    public String getTitle() {
        return title;
    }

    public LocalDate getCreateAt() {return createAt;}

    public int getAskedByClient() {return askedByClient;}

    public LocalDate getLegalDeadline() {return legalDeadline;}

    public boolean isTaken() {return isTaken;}

    public int getTakenByAttorney() {return takenByAttorney;}

    public LocalDate getTakenAt() {return takenAt;}

    public boolean isClose() {return isClose;}

    public int getRating() {return rating;}

    public List<Post> getPosts() {return posts;}

    public void setQuestionId(int questionId) {this.questionId = questionId;}

    public void setType(String type) {this.type = type;}

    public void setTitle(String title) {this.title = title;}

    public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

    public void setAskedByClient(int askedByClient) {this.askedByClient = askedByClient;}

    public void setLegalDeadline(LocalDate legalDeadline) {this.legalDeadline = legalDeadline;}

    public void setTaken(boolean taken) {isTaken = taken;}

    public void setTakenByAttorney(int takenByAttorney) {this.takenByAttorney = takenByAttorney;}

    public void setTakenAt(LocalDate takenAt) {this.takenAt = takenAt;}

    public void setClose(boolean close) {isClose = close;}

    public void setRating(int rating) {this.rating = rating;}

    public void addPosts(Post post) {
        if (! posts.contains(post)) {
            posts.add(post);
        }
    }

    @Override
    public int hashCode() {return Objects.hashCode(questionId);}

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Question)) return false;
        Question otherQuestion = (Question) obj;
        return questionId == otherQuestion.questionId;
    }

    @Override
    public String toString() {
        return String.format("This is a %s type question asked by %d", type, askedByClient);
    }
}
