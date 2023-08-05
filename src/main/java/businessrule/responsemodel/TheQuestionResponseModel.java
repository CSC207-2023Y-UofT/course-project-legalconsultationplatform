package businessrule.responsemodel;

import businessrule.usecase.PostDisplayFormatter;

import java.time.LocalDate;
import java.util.Map;
/**
 * This class represents providing a simple and convenient way to transfer questions data as a response to the client.
 */
public class TheQuestionResponseModel{
    private final int userId;
    private final String userName;
    private final String title;
    private final String type;
    private final LocalDate deadline;
    private final Map<Integer, PostDisplayFormatter> postMap;

    public TheQuestionResponseModel(int userId, String userName, String title, String type, LocalDate deadline, Map<Integer, PostDisplayFormatter> postMap) {
        this.userId = userId;
        this.userName = userName;
        this.title = title;
        this.type = type;
        this.deadline = deadline;
        this.postMap = postMap;
    }

    public int getUserId() {
        return userId;
    }

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

    public Map<Integer, PostDisplayFormatter> getPostMap() {
        return postMap;
    }
}
