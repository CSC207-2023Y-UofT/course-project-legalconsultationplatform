package businessrule.inputboundary;

import businessrule.outputboundary.HomePageOutputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

public interface CloseInputBoundary {
    HomePageResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
