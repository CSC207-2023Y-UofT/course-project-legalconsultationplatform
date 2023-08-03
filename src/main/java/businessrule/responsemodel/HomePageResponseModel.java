package businessrule.responsemodel;

public class HomePageResponseModel {
    private final int userId;
    private final String userName;

    public HomePageResponseModel(int userId, String userName) {
        this.userId = userId;
        this.userName = userName;
    }

    public int getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }
}
