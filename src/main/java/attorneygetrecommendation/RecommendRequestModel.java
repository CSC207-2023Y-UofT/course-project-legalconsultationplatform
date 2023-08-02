package attorneygetrecommendation;

public class RecommendRequestModel {
    private int attorneyId;

    public RecommendRequestModel(int attorneyId) {
        this.attorneyId = attorneyId;
    }

    public int getAttorneyId() {
        return attorneyId;
    }

    public void setAttorneyId(int attorneyId) {
        this.attorneyId = attorneyId;
    }
}
