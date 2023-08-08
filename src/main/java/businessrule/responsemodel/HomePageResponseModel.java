package businessrule.responsemodel;

/**
 * This class represents providing a simple and convenient way to transfer home page data
 * as a response to the client.
 */
public class HomePageResponseModel {

    /**
     * The unique identifier of the user.
     */
    private final int userId;

    /**
     * The name of the user.
     */
    private final String userName;

    /**
     * The type of the user (e.g., "admin", "user").
     */
    private final String userType;

    /**
     * Constructs a new HomePageResponseModel object with the specified user information.
     *
     * @param userId The unique identifier of the user.
     * @param userName The name of the user.
     * @param userType The type of the user.
     */
    public HomePageResponseModel(int userId, String userName, String userType) {
        this.userId = userId;
        this.userName = userName;
        this.userType = userType;
    }

    /**
     * Get the unique identifier of the user.
     *
     * @return The user's unique identifier.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Get the name of the user.
     *
     * @return The user's name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get the type of the user.
     *
     * @return The user's type.
     */
    public String getUserType() {
        return userType;
    }
}