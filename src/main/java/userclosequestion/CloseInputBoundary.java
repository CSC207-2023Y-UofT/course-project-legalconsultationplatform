package userclosequestion;

import presenter.MessageResponseModel;
import presenter.TheQuestionResponseModel;

public interface CloseInputBoundary {
    MessageResponseModel closeQuestion(CloseRequestModel closeRequestModel);
}
