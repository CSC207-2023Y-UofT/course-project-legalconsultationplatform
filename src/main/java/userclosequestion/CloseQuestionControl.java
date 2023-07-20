package userclosequestion;

import presenter.TheQuestionResponseModel;

public class CloseQuestionControl {
    final CloseInputBoundary closeInputBoundary;

    public CloseQuestionControl(CloseInputBoundary closeInputBoundary) {
        this.closeInputBoundary = closeInputBoundary;
    }
    public TheQuestionResponseModel closeQuestion(int questionId, int userId){
        CloseRequestModel closeRequestModel = new CloseRequestModel(questionId, userId);
        return closeInputBoundary.closeQuestion(closeRequestModel);
    }
}

