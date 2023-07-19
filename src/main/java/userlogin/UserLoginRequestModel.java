package userlogin;

public class UserLoginRequestModel {
    private final int userId;
    private final String password;

    public UserLoginRequestModel(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    public int getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }
}
