package businessrule.responsemodel;

import businessrule.usecase.util.PostDisplayFormatter;

import java.time.LocalDate;
import java.util.Map;

public class TheQuestionResponseModel{
    private final int userId;
    private final int questionId;
    private final String userName;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final boolean isClose;
    private final boolean isClient;
    private final Map<Integer, PostDisplayFormatter> postMap;

    public TheQuestionResponseModel(int userId, int questionId, String userName, String title, String type, LocalDate deadline, boolean isClose, boolean isClient, Map<Integer, PostDisplayFormatter> postMap) {
        this.userId = userId;
        this.questionId = questionId;
        this.userName = userName;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.isClose = isClose;
        this.isClient = isClient;
        this.postMap = postMap;
    }

    public int getUserId() {
        return userId;
    }

    public int getQuestionId() {return questionId;}

    public String getUserName() {
        return userName;
    }

    public String getTitle() {
        return title;
    }

    public String getType() {
        return type;
    }

    public LocalDate getDeadline() {
        return deadline;
    }

    public boolean isClose() {return isClose;}

    public boolean isClient() {return isClient;}

    public Map<Integer, PostDisplayFormatter> getPostMap() {
        return postMap;
    }
}
