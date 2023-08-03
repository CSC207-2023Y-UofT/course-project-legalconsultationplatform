package businessrule.inputboundary;

import businessrule.responsemodel.ViewResponseModel;
import businessrule.requestmodel.ViewRequestModel;

public interface ViewInputBoundary {
    ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel);
}
