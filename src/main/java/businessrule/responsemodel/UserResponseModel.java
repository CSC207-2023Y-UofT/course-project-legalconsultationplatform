package businessrule.responsemodel;

/**
 * This class represents a response model to store the basic information for the user specific output.
 */
public class UserResponseModel extends BaseResponseModel{
    private final int userId;
    private final String userName;
    private final String userType;

    /**
     * Constructs a new UserResponseModel object with the specified user information.
     *
     * @param userId The unique identifier of the user.
     * @param userName The name of the user.
     * @param userType The type of the user.
     */
    public UserResponseModel(int userId, String userName, String userType) {
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

    public TheQuestionResponseModel.Builder toQuestionResponseBuilder() {
        return new TheQuestionResponseModel.Builder(this);
    }

    public ViewResponseModel.Builder toViewResponseBuilder() {
        return new ViewResponseModel.Builder(this);
    }
}
