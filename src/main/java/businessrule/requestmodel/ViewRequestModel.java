package businessrule.requestmodel;

public class ViewRequestModel {
    private int userId;

    public ViewRequestModel(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
