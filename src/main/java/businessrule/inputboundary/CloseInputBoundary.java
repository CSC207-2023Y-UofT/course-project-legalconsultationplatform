package businessrule.inputboundary;

import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public interface CloseInputBoundary {
    HomePageResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
