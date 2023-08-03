package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a class representing a question thread.
 *
 * Each question thread contain information about the type of the question, the client who asked the question,
 * whether the question has been taken and who took the question, and a list of post belongs to the question.
 */
@Entity
public class Question {
    @Id
    private int questionId;
    private String type;
    private String title;
    private LocalDate createAt;
    private int askedByClient;
    private LocalDate legalDeadline;
    private boolean isTaken;
    private int takenByAttorney;
    private LocalDate takenAt;
    private boolean isClose;
    private int rating;
    @OneToMany
    private List<Post> posts;

    public static final int MISSING_RATING = -1;


    public Question() {
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

    public void setCreateAt(LocalDate createAt) {this.createAt = createAt;}

    public void setAskedByClient(int askedByClient) {this.askedByClient = askedByClient;}

    public void setLegalDeadline(LocalDate legalDeadline) {this.legalDeadline = legalDeadline;}

    public void setTaken(boolean taken) {isTaken = taken;}

    public void setTakenByAttorney(int takenByAttorney) {this.takenByAttorney = takenByAttorney;}

    public void setTakenAt(LocalDate takenAt) {this.takenAt = takenAt;}

    public void setClose(boolean close) {isClose = close;}

    public void setRating(int rating) {this.rating = rating;}

    public void addPosts(Post post) {this.posts.add(post);}

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
