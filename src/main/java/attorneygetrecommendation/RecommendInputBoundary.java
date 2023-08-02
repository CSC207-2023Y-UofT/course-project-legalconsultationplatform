package attorneygetrecommendation;

import presenter.ViewResponseModel;

public interface RecommendInputBoundary {
    public ViewResponseModel getMatching(RecommendRequestModel recommendRequestModel);
}
