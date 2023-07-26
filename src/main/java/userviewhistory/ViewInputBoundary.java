package userviewhistory;

import presenter.MessageResponseModel;
import presenter.ViewResponseModel;

public interface ViewInputBoundary {
    ViewResponseModel viewQuestion(ViewRequestModel viewRequestModel);
}
