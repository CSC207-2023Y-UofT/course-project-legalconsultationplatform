package businessrule.responsemodel;

public class UserResponseModel extends BaseResponseModel{
    private final int userId;
    private final String userName;
    private final String userType;

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
