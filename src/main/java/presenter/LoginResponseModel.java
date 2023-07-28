package presenter;

import java.time.LocalTime;

public class LoginResponseModel {
    int userId;
    LocalTime creationTime;
    String msg;
    boolean isSuccess;

    public LoginResponseModel(int userId, LocalTime creationTime) {
        this.userId = userId;
        this.creationTime = creationTime;
        this.isSuccess = true;
    }

    public LoginResponseModel(String msg) {
        this.msg = msg;
        this.isSuccess = false;
    }

    public int getUserId() {
        return userId;
    }

    public LocalTime getCreationTime() {
        return creationTime;
    }

    public String getMsg() {
        return msg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setCreationTime(LocalTime creationTime) {
        this.creationTime = creationTime;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }
}
