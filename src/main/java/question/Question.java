package question;

import java.util.List;
import java.util.ArrayList;

/**
 * This is a class representing a question thread.
 *
 * Each question thread contain information about the type of the question, the client who asked the question,
 * whether the question has been taken and who took the question, and a list of post belongs to the question.
 */
public class Question {

    private final int askedByClient;
    private boolean isTaken;
    private int takenByAttorney;
    private boolean isClose;
    private int rating;
    private List<Post> posts;
    private Integer questionId;
    /**
     * Constructs a new question
     *
     * @param clientId id of the client who asked the question
     */
    public Question(int clientId) {
        askedByClient = clientId;
        posts = new ArrayList<Post>();
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

    /**
     *
     *
     * @return an int id represents the question
     */
    public Integer getQuestionId() {
        return questionId;
    }
    /**
     *
     * @param questionId set the id for the question
     */
    public void setQuestionId(Integer questionId) {
        this.questionId = questionId;
    }

}
