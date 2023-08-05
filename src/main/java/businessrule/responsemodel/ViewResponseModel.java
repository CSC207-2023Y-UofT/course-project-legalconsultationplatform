package businessrule.responsemodel;

import businessrule.usecase.QuestionDisplayFormatter;

import java.util.List;
import java.util.Map;
/**
 * This class represents providing a simple and convenient way to transfer view data as a response to the client.
 */
public class ViewResponseModel {
    private final int userId;
    private final String userName;
    private final Map<Integer, QuestionDisplayFormatter> questionMap;

    public ViewResponseModel(int userId, String userName, Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.userId = userId;
        this.userName = userName;
        this.questionMap = questionMap;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public Map<Integer, QuestionDisplayFormatter> getQuestionMap() {
        return questionMap;
    }
}
