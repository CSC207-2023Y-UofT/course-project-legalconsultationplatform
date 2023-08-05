package businessrule.responsemodel;

public class HomePageResponseModel {
    private final int userId;
    private final String userName;
    private final String userType;

    public HomePageResponseModel(int userId, String userName, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserType() {
        return userType;
    }
}
