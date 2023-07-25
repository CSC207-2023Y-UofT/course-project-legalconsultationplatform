package questionentities;

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
    private LocalDate createAt;
    private int askedByClient;
    private LocalDate legalDeadline;
    private boolean isTaken;
    private int takenByAttorney;
    private boolean isClose;
    private int rating;
    @OneToMany
    private List<Post> posts;

    public static final int MISSING_RATING = -1;


    public Question() {
    }

    public Question(int questionId, String type, LocalDate createAt, int askedByClient, LocalDate legalDeadline) {
        this.questionId = questionId;
        this.type = type;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.legalDeadline = legalDeadline;
        this.rating = MISSING_RATING;
        this.posts = new ArrayList<Post>();
    }

    public Question(int questionId, String type, LocalDate createAt, int askedByClient, boolean isTaken, int takenByAttorney,
                    boolean isClose, int rating, List<Post> posts, LocalDate legalDeadline) {
        this.questionId = questionId;
        this.type = type;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.isTaken = isTaken;
        this.takenByAttorney = takenByAttorney;
        this.isClose = isClose;
        this.rating = rating;
        this.posts = posts;
        this.legalDeadline = legalDeadline;
    }

    /**
     *
     *
     * @return an int id represents the question
     */
    public int getQuestionId() {
        return questionId;
    }

    public String getType() {
        return type;
    }

    /**
     *
     * @return the client id of the client who asked the question
     */
    public int getAskedByClient() {
        return askedByClient;
    }

    /**
     *
     * @return true iff the question has been answered by an attorney
     */
    public boolean isTaken() {
        return isTaken;
    }

    /**
     *
     * @param taken whether the question has been taken
     */
    public void setIsTaken(boolean taken) {
        isTaken = taken;
    }

    /**
     *
     * @return the attorney id of the attorney who answered the question
     */
    public int getTakenByAttorney() {
        return takenByAttorney;
    }

    /**
     *
     * @param takenByAttorney the attorney id of the attorney who answered the question
     */
    public void setTakenByAttorney(int takenByAttorney) {
        this.takenByAttorney = takenByAttorney;
    }

    /**
     *
     * @return true iff the question has been closed
     */
    public boolean isClose() {
        return isClose;
    }

    /**
     *
     * @param close whether the question has been closed
     */
    public void setClose(boolean close) {
        isClose = close;
    }

    /**
     *
     * @param rating rating of the question
     */
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     *
     * @return a list of posts that belongs to the question thread
     */
    public List<Post> getPosts() {
        return posts;
    }

    /**
     *
     * @param posts a list of posts
     */
    public void setPosts(List<Post> posts) {this.posts = posts;}

    /**
     * add post to the post list
     *
     * @param post a Post object to be added to the post list
     */
    public void addPosts(Post post) {
        this.posts.add(post);
    }

}
