package businessrule.requestmodel;

/**
 * This class represents a request model for viewing a specific entity.
 * It encapsulates the information required to retrieve entity data for a user.
 */
public class ViewRequestModel {
    private final int userId;

    /**
     * Constructs a ViewRequestModel object.
     *
     * @param userId The ID of the user requesting to view the entity.
     */
    public ViewRequestModel(int userId) {
        this.userId = userId;
    }

    /**
     * Get the ID of the user who wants to view the entity.
     *
     * @return The ID of the user.
     */
    public int getUserId() {
        return userId;
    }
}
