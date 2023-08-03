package askquestion;

import apapter.responsemodel.TheQuestionResponseModel;

public interface QuestionInputBoundary {
    TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}
