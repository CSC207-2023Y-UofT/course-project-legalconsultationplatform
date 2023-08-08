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

    public ControlContainer(ClientRegisterControl clientRegisterControl) {
        this.clientRegisterControl = clientRegisterControl;
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
