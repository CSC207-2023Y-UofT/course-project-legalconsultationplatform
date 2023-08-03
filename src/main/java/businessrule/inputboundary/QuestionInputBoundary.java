package businessrule.inputboundary;

import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.requestmodel.QuestionRequestModel;

public interface QuestionInputBoundary {
    TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}
