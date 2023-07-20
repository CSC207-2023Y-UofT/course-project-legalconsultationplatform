package userselectquestion;

import presenter.TheQuestionResponseModel;

public interface SelectInputBoundary {
    TheQuestionResponseModel selectQuestion(SelectRequestModel selectRequestModel);
}
