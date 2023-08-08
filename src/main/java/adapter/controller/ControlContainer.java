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
    ViewQuestionControl browseQuestionControl;
    ViewQuestionControl viewRateableQuestionControl;
    ViewQuestionControl recommendationControl;

    public ControlContainer(ClientRegisterControl clientRegisterControl, CloseQuestionControl closeQuestionControl,
                            PostControl postControl, QuestionControl questionControl, RateControl rateControl,
                            SelectQuestionControl selectQuestionControl, UserLoginControl userLoginControl,
                            ViewQuestionControl viewQuestionControl, ViewQuestionControl browseQuestionControl,
                            ViewQuestionControl viewRateableQuestionControl, ViewQuestionControl recommendationControl) {
        this.clientRegisterControl = clientRegisterControl;
        this.closeQuestionControl = closeQuestionControl;
        this.postControl = postControl;
        this.questionControl = questionControl;
        this.rateControl = rateControl;
        this.selectQuestionControl = selectQuestionControl;
        this.userLoginControl = userLoginControl;
        this.viewQuestionControl = viewQuestionControl;
        this.browseQuestionControl = browseQuestionControl;
        this.viewRateableQuestionControl = viewRateableQuestionControl;
        this.recommendationControl = recommendationControl;
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

    public ViewQuestionControl getBrowseQuestionControl() {return browseQuestionControl;}

    public ViewQuestionControl getViewRateableQuestionControl() {return viewRateableQuestionControl;}

    public ViewQuestionControl getRecommendationControl() {return recommendationControl;}
}
