package businessrule.requestmodel;

/**
 * This class represents a request model for browsing questions related to an attorney.
 */
public class BrowseRequestModel {
    private final int attorneyId;

    /**
     * Constructs a new BrowseRequestModel with the specified attorney ID.
     *
     * @param attorneyId The ID of the attorney for whom the questions are being browsed.
     */
    public BrowseRequestModel(int attorneyId) {
        this.attorneyId = attorneyId;
    }

    /**
     * Gets the attorney ID associated with this browse request.
     *
     * @return The attorney ID.
     */
    public int getAttorneyId() {
        return attorneyId;
    }
}