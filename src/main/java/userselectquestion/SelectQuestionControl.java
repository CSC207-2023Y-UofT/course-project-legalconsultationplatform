package userselectquestion;

public class SelectQuestionControl {
    final SelectInputBoundary selectInputBoundary;

    public SelectQuestionControl(SelectInputBoundary selectInputBoundary) {
        this.selectInputBoundary = selectInputBoundary;
    }
    public TheQuestionResponseModel selectQuestion(int questionId, int userId) {
        SelectRequestModel selectRequestModel =  new SelectRequestModel(questionId, userId);
        return selectInputBoundary.selectQuestion(selectRequestModel);
    }
}
