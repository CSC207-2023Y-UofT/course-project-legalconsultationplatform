package businessrule.inputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.UserResponseModel;

public interface QuestionInputBoundary {
    UserResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}
