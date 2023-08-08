package adapter.controller;

public class ControlContainer {
    ClientRegisterControl clientRegisterControl;
    CloseQuestionControl closeQuestionControl;
    PostControl postControl;
    QuestionControl questionControl;
    RateControl rateControl;
    SelectQuestionControl selectQuestionControl;
    UserLoginControl userLoginControl;
    ViewQuestionControl viewQuestionControl;

    public ControlContainer(ClientRegisterControl clientRegisterControl, CloseQuestionControl closeQuestionControl, PostControl postControl, QuestionControl questionControl, RateControl rateControl, SelectQuestionControl selectQuestionControl, UserLoginControl userLoginControl, ViewQuestionControl viewQuestionControl) {
        this.clientRegisterControl = clientRegisterControl;
        this.closeQuestionControl = closeQuestionControl;
        this.postControl = postControl;
        this.questionControl = questionControl;
        this.rateControl = rateControl;
        this.selectQuestionControl = selectQuestionControl;
        this.userLoginControl = userLoginControl;
        this.viewQuestionControl = viewQuestionControl;
    }

    public ClientRegisterControl getClientRegisterControl() {
        return clientRegisterControl;
    }

    public CloseQuestionControl getCloseQuestionControl() {
        return closeQuestionControl;
    }

    public PostControl getPostControl() {
        return postControl;
    }

    public QuestionControl getQuestionControl() {
        return questionControl;
    }

    public RateControl getRateControl() {
        return rateControl;
    }

    public SelectQuestionControl getSelectQuestionControl() {
        return selectQuestionControl;
    }

    public UserLoginControl getUserLoginControl() {
        return userLoginControl;
    }

    public ViewQuestionControl getViewQuestionControl() {
        return viewQuestionControl;
    }
}
