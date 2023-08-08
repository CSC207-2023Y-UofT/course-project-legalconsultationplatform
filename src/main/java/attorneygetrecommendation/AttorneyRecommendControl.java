package attorneygetrecommendation;

import adapter.controller.ViewQuestionControllerBase;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.responsemodel.ViewResponseModel;

public class AttorneyRecommendControl extends ViewQuestionControllerBase {

    public AttorneyRecommendControl(ViewInputBoundary viewInputBoundary) {
        super(viewInputBoundary);
    }

    public ViewResponseModel getMatching(int attorneyId) {
        RecommendRequestModel recommendRequestModel = new RecommendRequestModel(attorneyId);
        return recommendInputBoundary.getMatching(recommendRequestModel);
    }
}
