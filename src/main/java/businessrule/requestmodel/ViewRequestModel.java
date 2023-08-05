package businessrule.requestmodel;

/**
 * This class represents request model for viewing questions.
 */
public class ViewRequestModel {
    private final int userId;

    public ViewRequestModel(int userId) {
        this.userId = userId;
    }

    public int getUserId() {
        return userId;
    }
}
