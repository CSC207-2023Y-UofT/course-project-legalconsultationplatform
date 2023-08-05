package businessrule.responsemodel;

/**
 * This class represents providing a simple and convenient way to transfer home page data
 * as a response to the client.
 */
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
