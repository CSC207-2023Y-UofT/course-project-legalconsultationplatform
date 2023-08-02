package userlogin;

import java.time.LocalTime;

public class LoginResponseModel {
    int userId;
    String creationTime;

    public LoginResponseModel(int userId, String creationTime) {
        this.userId = userId;
        this.creationTime = creationTime;
    }

    public int getUserId() {
        return userId;
    }
    public String getCreationTime() {
        return creationTime;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }
}
