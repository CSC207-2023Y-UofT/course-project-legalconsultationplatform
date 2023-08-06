package attorneygetrecommendation;

import businessrule.responsemodel.ViewResponseModel;

public interface RecommendInputBoundary {
    public ViewResponseModel getMatching(RecommendRequestModel recommendRequestModel);
}
