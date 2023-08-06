package attorneygetrecommendation;

import businessrule.responsemodel.ViewResponseModel;

public class AttorneyRecommendControl {
    private final RecommendInputBoundary recommendInputBoundary;

    public AttorneyRecommendControl(RecommendInputBoundary recommendInputBoundary) {
        this.recommendInputBoundary = recommendInputBoundary;
    }

    public ViewResponseModel getMatching(int attorneyId) {
        RecommendRequestModel recommendRequestModel = new RecommendRequestModel(attorneyId);
        return recommendInputBoundary.getMatching(recommendRequestModel);
    }
}
