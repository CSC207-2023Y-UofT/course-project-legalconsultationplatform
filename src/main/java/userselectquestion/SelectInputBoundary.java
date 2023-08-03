package userselectquestion;

import apapter.responsemodel.TheQuestionResponseModel;

public interface SelectInputBoundary {
    TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel);
}
