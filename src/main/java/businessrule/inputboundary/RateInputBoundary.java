package businessrule.inputboundary;

import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public interface RateInputBoundary {

    HomePageResponseModel rateAnswer(RateRequestModel rateRequestModel);

}
