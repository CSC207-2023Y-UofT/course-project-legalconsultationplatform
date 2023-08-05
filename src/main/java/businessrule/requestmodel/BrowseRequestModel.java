package businessrule.requestmodel;

/**
 * This class represents a request model for browsing questions related to an attorney.
 */
public class BrowseRequestModel {
    private final int attorneyId;

    public BrowseRequestModel(int attorneyId) {
        this.attorneyId = attorneyId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }
}
