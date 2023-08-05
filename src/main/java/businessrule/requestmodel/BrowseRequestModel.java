package businessrule.requestmodel;

public class BrowseRequestModel {
    private final int attorneyId;

    public BrowseRequestModel(int attorneyId) {
        this.attorneyId = attorneyId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }
}
