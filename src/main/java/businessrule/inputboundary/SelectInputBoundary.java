package businessrule.inputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface SelectInputBoundary {
    UserResponseModel selectQuestion(SelectRequestModel selectRequestModel);
}
