package businessrule.responsemodel;

import businessrule.usecase.util.PostDisplayFormatter;

import java.time.LocalDate;
import java.util.Map;

/**
 * This class represents a question with detailed informatio.
 * It is including its user, title, type, deadline, closure status, and associated posts.
 */
public class TheQuestionResponseModel {

    private final int userId;
    private final int questionId;
    private final String userName;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final boolean isClose;
    private final Map<Integer, PostDisplayFormatter> postMap;

    public TheQuestionResponseModel(int userId, int questionId, String userName, String title, String type, LocalDate deadline, boolean isClose, Map<Integer, PostDisplayFormatter> postMap) {
        this.userId = userId;
        this.questionId = questionId;
        this.userName = userName;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.isClose = isClose;
        this.postMap = postMap;
    }

    /**
     * Get the user ID associated with the question.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the question ID.
     *
     * @return The question ID.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Get the user name associated with the question.
     *
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the title of the question.
     *
     * @return The question title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Get the type of the question.
     *
     * @return The question type.
     */
    public String getType() {
        return type;
    }

    /**
     * Get the deadline of the question.
     *
     * @return The question deadline.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Check if the question is closed.
     *
     * @return True if the question is closed, false otherwise.
     */
    public boolean isClose() {
        return isClose;
    }

    /**
     * Get the map of associated posts with their display formatting.
     *
     * @return The map of post IDs and their associated display formatting.
     */
    public Map<Integer, PostDisplayFormatter> getPostMap() {
        return postMap;
    }
}