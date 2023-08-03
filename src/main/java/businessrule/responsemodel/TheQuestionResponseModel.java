package businessrule.responsemodel;

import businessrule.usecase.PostDisplayFormatter;

import java.time.LocalDate;
import java.util.Map;

public class TheQuestionResponseModel{
    private int userId;
    private String userName;
    private String title;
    private String type;
    private LocalDate deadline;
    private Map<Integer, PostDisplayFormatter> postMap;

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

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Map<Integer, PostDisplayFormatter> getPostMap() {return postMap;}

    public void setPostMap(Map<Integer, PostDisplayFormatter> postMap) {this.postMap = postMap;}

    public String getTitle() {return title;}

    public void setTitle(String title) {this.title = title;}

    public String getType() {return type;}

    public void setType(String type) {this.type = type;}

    public LocalDate getDeadline() {return deadline;}

    public void setDeadline(LocalDate deadline) {this.deadline = deadline;}
}
