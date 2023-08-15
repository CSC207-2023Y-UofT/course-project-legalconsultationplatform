package businessrule.requestmodel;

/**
 * This class represents a request model for rating an answer.
 */
public class RateRequestModel {

    private final int rating;
    private final int answerId;

    /**
     * Constructs a RateRequestModel with the specified parameters.
     *
     * @param rating   The rating value for the answer.
     * @param answerId The ID of the answer being rated.
     */
    public RateRequestModel(int rating, int answerId) {
        this.rating = rating;
        this.answerId = answerId;
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
}
