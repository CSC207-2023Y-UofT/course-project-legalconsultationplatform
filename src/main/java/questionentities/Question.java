package questionentities;

import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

/**
 * This is a class representing a question thread.
 *
 * Each question thread contain information about the type of the question, the client who asked the question,
 * whether the question has been taken and who took the question, and a list of post belongs to the question.
 */
public class Question {
    private final int questionId;
    private final LocalDate createAt;
    private final int askedByClient;
    private final LocalDate legalDeadline;
    private boolean isTaken;
    private int takenByAttorney;
    private boolean isClose;
    private int rating;
    private List<Post> posts;


    public Question(int questionId, LocalDate createAt, int askedByClient, LocalDate legalDeadline) {
        this.questionId = questionId;
        this.createAt = createAt;
        this.askedByClient = askedByClient;
        this.legalDeadline = legalDeadline;
        this.posts = new ArrayList<Post>();
    }

    public Question(int questionId, LocalDate createAt, int askedByClient, boolean isTaken, int takenByAttorney,
                    boolean isClose, int rating, List<Post> posts, LocalDate legalDeadline) {
        this.questionId = questionId;
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
     * add post to the post list
     *
     * @param post a Post object to be added to the post list
     */
    public void addPosts(Post post) {
        this.posts.add(post);
    }

}
