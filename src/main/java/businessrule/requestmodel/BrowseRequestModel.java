package businessrule.requestmodel;

public class BrowseRequestModel {
    private int attorneyId;

    public BrowseRequestModel(int attorneyId) {
        this.attorneyId = attorneyId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(int attorneyId) {
        this.attorneyId = attorneyId;
    }
}
