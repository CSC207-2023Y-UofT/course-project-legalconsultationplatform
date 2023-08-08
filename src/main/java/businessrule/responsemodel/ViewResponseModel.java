package businessrule.responsemodel;

import businessrule.usecase.QuestionDisplayFormatter;

import java.util.List;
import java.util.Map;

/**
 * This class represents a response model that provides a simple and convenient way to transfer view data as a response to the client.
 */
public class ViewResponseModel {
    private final int userId;
    private final String userName;
    private final Map<Integer, QuestionDisplayFormatter> questionMap;

    /**
     * Constructs a new ViewResponseModel with the specified user ID, user name, and question map.
     *
     * @param userId The ID of the user associated with the response.
     * @param userName The name of the user associated with the response.
     * @param questionMap A map containing question IDs as keys and their corresponding QuestionDisplayFormatters as values.
     */
    public ViewResponseModel(int userId, String userName, Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.userId = userId;
        this.userName = userName;
        this.questionMap = questionMap;
    }

    /**
     * Get the ID of the user associated with this response.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the name of the user associated with this response.
     *
     * @return The user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the map of question IDs and their corresponding QuestionDisplayFormatters.
     *
     * @return The question map.
     */
    public Map<Integer, QuestionDisplayFormatter> getQuestionMap() {
        return questionMap;
    }
}