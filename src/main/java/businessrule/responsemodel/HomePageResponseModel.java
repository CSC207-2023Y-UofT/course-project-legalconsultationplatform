package businessrule.responsemodel;

/**
 * This class represents providing a simple and convenient way to transfer home page data
 * as a response to the client.
 */
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
