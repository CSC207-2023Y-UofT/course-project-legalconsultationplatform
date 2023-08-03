package businessrule.responsemodel;

import businessrule.usecase.QuestionDisplayFormatter;

import java.util.List;
import java.util.Map;

public class ViewResponseModel {
    int userId;
    String userName;
    Map<Integer, QuestionDisplayFormatter> questionMap;

    public ViewResponseModel(int userId, String userName, Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.userId = userId;
        this.userName = userName;
        this.questionMap = questionMap;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Integer, QuestionDisplayFormatter> getQuestionMap() {
        return questionMap;
    }

    public void setQuestionMap(Map<Integer, QuestionDisplayFormatter> questionMap) {
        this.questionMap = questionMap;
    }
}
