package businessrule.requestmodel;

public class RateRequestModel {

    private final int rating;
    private final int answerId;

    public RateRequestModel(int rating, int answerId) {
        this.rating = rating;
        this.answerId = answerId;
    }

    public int getRating() {
        return rating;
    }

    public int getAnswerId() {
        return answerId;
    }

}
