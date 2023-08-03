package businessrule.inputboundary;

import businessrule.requestmodel.RateRequestModel;

public interface RateInputBoundary {

    MessageResponseModel rateAnswer(RateRequestModel rateRequestModel);

}
