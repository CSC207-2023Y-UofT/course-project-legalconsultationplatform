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
 * This class represents a question thread.
 *
 * Each question thread contains information about the type of the question, the client who asked the question,
 * whether the question has been taken and by whom, and a list of posts belonging to the question.
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

    /**
     * Constructs a new Question instance.
     *
     * @param questionId    The ID of the question.
     * @param type          The type of the question.
     * @param title         The title of the question.
     * @param createAt      The creation date of the question.
     * @param askedByClient The ID of the client who asked the question.
     * @param legalDeadline The legal deadline for the question.
     */
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

    /**
     * Gets the ID of the question.
     *
     * @return The question ID.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Gets the type of the question.
     *
     * @return The question type.
     */
    public String getType() {
        return type;
    }

    /**
     * Gets the title of the question.
     *
     * @return The question title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Gets the creation date of the question.
     *
     * @return The creation date.
     */
    public LocalDate getCreateAt() {
        return createAt;
    }

    /**
     * Gets the ID of the client who asked the question.
     *
     * @return The client ID.
     */
    public int getAskedByClient() {
        return askedByClient;
    }

    /**
     * Gets the legal deadline for the question.
     *
     * @return The legal deadline.
     */
    public LocalDate getLegalDeadline() {
        return legalDeadline;
    }

    /**
     * Checks if the question has been taken.
     *
     * @return True if the question is taken, false otherwise.
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     * Gets the ID of the attorney who took the question.
     *
     * @return The attorney ID.
     */
    public int getTakenByAttorney() {
        return takenByAttorney;
    }

    /**
     * Gets the date when the question was taken.
     *
     * @return The taken date.
     */
    public LocalDate getTakenAt() {
        return takenAt;
    }

    /**
     * Checks if the question is closed.
     *
     * @return True if the question is closed, false otherwise.
     */
    public boolean isClose() {
        return isClose;
    }

    /**
     * Gets the rating of the question.
     *
     * @return The question rating.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Gets the list of posts belonging to the question.
     *
     * @return The list of posts.
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     * Sets the ID of the question.
     *
     * @param questionId The question ID to set.
     */
    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    /**
     * Sets the type of the question.
     *
     * @param type The question type to set.
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * Sets the title of the question.
     *
     * @param title The question title to set.
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Sets the creation date of the question.
     *
     * @param createAt The creation date to set.
     */
    public void setCreateAt(LocalDate createAt) {
        this.createAt = createAt;
    }

    /**
     * Sets the ID of the client who asked the question.
     *
     * @param askedByClient The client ID to set.
     */
    public void setAskedByClient(int askedByClient) {
        this.askedByClient = askedByClient;
    }

    /**
     * Sets the legal deadline for the question.
     *
     * @param legalDeadline The legal deadline to set.
     */
    public void setLegalDeadline(LocalDate legalDeadline) {
        this.legalDeadline = legalDeadline;
    }

    /**
     * Sets whether the question is taken or not.
     *
     * @param taken True if taken, false otherwise.
     */
    public void setTaken(boolean taken) {
        isTaken = taken;
    }

    /**
     * Sets the ID of the attorney who took the question.
     *
     * @param takenByAttorney The attorney ID to set.
     */
    public void setTakenByAttorney(int takenByAttorney) {
        this.takenByAttorney = takenByAttorney;
    }

    /**
     * Sets the date when the question was taken.
     *
     * @param takenAt The taken date to set.
     */
    public void setTakenAt(LocalDate takenAt) {
        this.takenAt = takenAt;
    }

    /**
     * Sets whether the question is closed or not.
     *
     * @param close True if closed, false otherwise.
     */
    public void setClose(boolean close) {
        isClose = close;
    }

    /**
     * Sets the rating of the question.
     *
     * @param rating The question rating to set.
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * Adds a post to the list of posts belonging to the question.
     *
     * @param post The post to add.
     */
    public void addPosts(Post post) {
        if (!posts.contains(post)) {
            posts.add(post);
        }
    }


    /**
     * Generates a hash code for the Question object based on its questionId.
     *
     * @return The hash code value.
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(questionId);
    }

    /**
     * Compares this Question object with another object to determine equality.
     *
     * @param obj The object to compare with.
     * @return True if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Question)) return false;
        Question otherQuestion = (Question) obj;
        return questionId == otherQuestion.questionId;
    }

    /**
     * Returns a string representation of the Question object.
     *
     * @return The formatted string.
     */
    @Override
    public String toString() {
        return String.format("This is a %s type question asked by %d", type, askedByClient);
    }
}