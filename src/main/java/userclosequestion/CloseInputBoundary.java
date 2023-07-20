package userclosequestion;

import presenter.TheQuestionResponseModel;

public interface CloseInputBoundary {
    TheQuestionResponseModel closeQuestion(CloseRequestModel colseRequestModel);
}
