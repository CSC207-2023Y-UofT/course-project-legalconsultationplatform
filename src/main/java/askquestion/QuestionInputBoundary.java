package askquestion;

import presenter.TheQuestionResponseModel;

public interface QuestionInputBoundary {
    TheQuestionResponseModel createQuestion(QuestionRequestModel questionRequestModel);
}
