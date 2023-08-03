package businessrule.inputboundary;

import businessrule.requestmodel.CloseRequestModel;

public interface CloseInputBoundary {
    MessageResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
