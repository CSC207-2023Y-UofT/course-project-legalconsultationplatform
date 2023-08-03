package businessrule.responsemodel;

import javax.print.DocFlavor;
import java.util.List;
import java.util.Map;

public class TheQuestionResponseModel {
    int userId;
    String userName;
    Map<Integer, List<Object>> postMap;

    public TheQuestionResponseModel(int userId, String userName, Map<Integer, List<Object>> postMap) {
        this.userId = userId;
        this.userName = userName;
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

    public Map<Integer, List<Object>> getPostMap() {
        return postMap;
    }

    public void setPostMap(Map<Integer, List<Object>> postMap) {
        this.postMap = postMap;
    }
}
