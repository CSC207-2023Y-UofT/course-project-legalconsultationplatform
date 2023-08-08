package businessrule.responsemodel;

import businessrule.usecase.PostDisplayFormatter;

import java.time.LocalDate;
import java.util.Map;

/**
 * This class represents providing a simple and convenient way to transfer questions data as a response to the client.
 */
public class TheQuestionResponseModel {
    private final int userId;
    private final int questionId;
    private final String userName;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final Map<Integer, PostDisplayFormatter> postMap;

    /**
     * Constructs a new TheQuestionResponseModel object with the provided data.
     *
     * @param userId The ID of the user associated with the question response.
     * @param questionId The ID of the question associated with the response.
     * @param userName The name of the user associated with the question response.
     * @param title The title of the question.
     * @param type The type of the question.
     * @param deadline The deadline associated with the question.
     * @param postMap A map containing post IDs as keys and corresponding PostDisplayFormatter objects as values.
     */
    public TheQuestionResponseModel(int userId, int questionId, String userName, String title, String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        this.userId = userId;
        this.questionId = questionId;
        this.userName = userName;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.postMap = postMap;
    }

    /**
     * Returns the ID of the user associated with the question response.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Returns the ID of the question associated with the response.
     *
     * @return The question ID.
     */
    public int getQuestionId() {
        return questionId;
    }

    /**
     * Returns the name of the user associated with the question response.
     *
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Returns the title of the question.
     *
     * @return The question title.
     */
    public String getTitle() {
        return title;
    }

    /**
     * Returns the type of the question.
     *
     * @return The question type.
     */
    public String getType() {
        return type;
    }

    /**
     * Returns the deadline associated with the question.
     *
     * @return The question deadline.
     */
    public LocalDate getDeadline() {
        return deadline;
    }

    /**
     * Returns a map containing post IDs as keys and corresponding PostDisplayFormatter objects as values.
     *
     * @return The map of post IDs and PostDisplayFormatter objects.
     */
    public Map<Integer, PostDisplayFormatter> getPostMap() {
        return postMap;
    }
}
