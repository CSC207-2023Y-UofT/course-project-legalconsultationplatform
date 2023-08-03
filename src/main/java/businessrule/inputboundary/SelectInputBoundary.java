package businessrule.inputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.requestmodel.SelectRequestModel;

public interface SelectInputBoundary {
    TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel);
}
