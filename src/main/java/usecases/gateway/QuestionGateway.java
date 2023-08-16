package usecases.gateway;

import entities.Post;
import entities.Question;
import java.time.LocalDate;
import java.util.List;

/**
 * This interface provides methods for retrieving questions, managing question status and attributes,
 * and obtaining posts.
 */
public interface QuestionGateway extends GenericGateway<Question> {

    /**
     * Get a list of questions that have not been taken by an attorney.
     *
     * @return A list of questions not yet taken by any attorney.
     */
    List<Question> getNotTakenQuestion();

    /**
     * Get a list of questions that are not closed.
     *
     * @return A list of questions that are still open and not closed.
     */
    List<Question> getNotClosedQuestion();

    /**
     * Get all posts associated with a specific question.
     *
     * @param questionId The ID of the question to retrieve posts for.
     * @return A list of posts associated with the specified question.
     */
    List<Post> getAllPostOfQuestion(int questionId);

    /**
     * Update the "isTaken" status of a question.
     *
     * @param questionId The ID of the question to update.
     * @param isTaken The new "isTaken" status.
     */
    void updateIsTaken(int questionId, boolean isTaken);

    /**
     * Update the attorney who took a specific question.
     *
     * @param questionId The ID of the question to update.
     * @param attorneyId The ID of the attorney who took the question.
     */
    void updateTakenByAttorney(int questionId, int attorneyId);

    /**
     * Update the "isClose" status of a question.
     *
     * @param questionId The ID of the question to update.
     * @param isClose The new "isClose" status.
     */
    void updateIsClose(int questionId, boolean isClose);

    /**
     * Update the rating of a question.
     *
     * @param questionId The ID of the question to update.
     * @param rating The new rating value.
     */
    void updateRating(int questionId, int rating);

    /**
     * Update the time when a question was taken.
     *
     * @param questionId The ID of the question to update.
     * @param time The new time when the question was taken.
     */
    void updateTakenAt(int questionId, LocalDate time);

    /**
     * Update the posts associated with a question.
     *
     * @param id The ID of the question to update.
     * @param post The post to add to the question's posts.
     */
    void updatePosts(int id, Post post);

    /**
     * Get a question by its ID.
     *
     * @param id The ID of the question to retrieve.
     * @return The question object associated with the provided ID.
     */
    @Override
    Question get(int id);
}
