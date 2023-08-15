package usecases.requests;

/**
 * This class represents a user login request model.
 * It encapsulates the information needed for a user to perform a login operation.
 */
public class UserLoginRequestModel {
    private final int userId;
    private final String password;

    /**
     * Constructs a new UserLoginRequestModel with the provided user ID and password.
     *
     * @param userId   The unique identifier of the user.
     * @param password The user's password.
     */
    public UserLoginRequestModel(int userId, String password) {
        this.userId = userId;
        this.password = password;
    }

    /**
     * Retrieves the user ID from the login request.
     *
     * @return The user's unique identifier.
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Retrieves the password from the login request.
     *
     * @return The user's password.
     */
    public String getPassword() {
        return password;
    }
}
