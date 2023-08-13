package businessrule.inputboundary;

import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface RateInputBoundary {

    UserResponseModel rateAnswer(RateRequestModel rateRequestModel);

}
