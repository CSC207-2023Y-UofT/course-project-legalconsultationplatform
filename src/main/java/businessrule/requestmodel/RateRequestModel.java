package businessrule.requestmodel;

public class RateRequestModel {

    private final int rating;
    private final int answerId;
    private final int userId;

    public RateRequestModel(int rating, int answerId, int userId) {
        this.rating = rating;
        this.answerId = answerId;
        this.userId = userId;
    }

    public int getRating() {
        return rating;
    }

    public int getAnswerId() {
        return answerId;
    }

    public int getUserId() {
        return userId;
    }
}
