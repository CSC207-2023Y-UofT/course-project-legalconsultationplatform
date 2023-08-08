package businessrule.requestmodel;

/**
 * This class represents a request model for rating an answer.
 */
public class RateRequestModel {

    private final int rating;
    private final int answerId;
    private final int userId;

    /**
     * Constructs a RateRequestModel with the specified parameters.
     *
     * @param rating   The rating value for the answer.
     * @param answerId The ID of the answer being rated.
     * @param userId   The ID of the user rating the answer.
     */
    public RateRequestModel(int rating, int answerId, int userId) {
        this.rating = rating;
        this.answerId = answerId;
        this.userId = userId;
    }

    /**
     * Retrieves the rating value for the answer.
     *
     * @return The rating value.
     */
    public int getRating() {
        return rating;
    }

    /**
     * Retrieves the ID of the answer being rated.
     *
     * @return The answer ID.
     */
    public int getAnswerId() {
        return answerId;
    }

    /**
     * Retrieves the ID of the user rating the answer.
     *
     * @return The user ID.
     */
    public int getUserId() {
        return userId;
    }
}
