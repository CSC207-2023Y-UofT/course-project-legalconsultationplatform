package adapters.controllers;

/**
 * This class represents that holds instances of various control classes.
 * It is responsible for managing different aspects of the application.
 */
public class ControlContainer {
    RegisterControl clientRegisterControl;
    RegisterControl attorneyRegisterControl;
    CloseQuestionControl closeQuestionControl;
    PostControl postControl;
    QuestionControl questionControl;
    RateControl rateControl;
    SelectQuestionControl selectQuestionControl;
    UserLoginControl userLoginControl;
    ViewQuestionControl viewQuestionControl;
    ViewQuestionControl browseQuestionControl;
    ViewQuestionControl recommendationControl;

    /**
     * Constructor to initialize the ControlContainer with instances of various control classes.
     *
     * @param clientRegisterControl   The control class for client registration.
     * @param closeQuestionControl    The control class for closing questions.
     * @param postControl             The control class for handling posts.
     * @param questionControl         The control class for question management.
     * @param rateControl             The control class for rating questions.
     * @param selectQuestionControl   The control class for selecting questions.
     * @param userLoginControl        The control class for user login.
     * @param viewQuestionControl     The control class for viewing questions.
     * @param browseQuestionControl   The control class for browsing questions.
     * @param recommendationControl   The control class for viewing recommended questions.
     * @param attorneyRegisterControl The control class for attorney registration
     */
    public ControlContainer(RegisterControl clientRegisterControl, CloseQuestionControl closeQuestionControl,
                            PostControl postControl, QuestionControl questionControl, RateControl rateControl,
                            SelectQuestionControl selectQuestionControl, UserLoginControl userLoginControl,
                            ViewQuestionControl viewQuestionControl, ViewQuestionControl browseQuestionControl,
                            ViewQuestionControl recommendationControl, RegisterControl attorneyRegisterControl) {
        this.clientRegisterControl = clientRegisterControl;
        this.attorneyRegisterControl = attorneyRegisterControl;
        this.closeQuestionControl = closeQuestionControl;
        this.postControl = postControl;
        this.questionControl = questionControl;
        this.rateControl = rateControl;
        this.selectQuestionControl = selectQuestionControl;
        this.userLoginControl = userLoginControl;
        this.viewQuestionControl = viewQuestionControl;
        this.browseQuestionControl = browseQuestionControl;
        this.recommendationControl = recommendationControl;
    }


    public RegisterControl getClientRegisterControl() {
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

    public ViewQuestionControl getRecommendationControl() {return recommendationControl;}

    public RegisterControl getAttorneyRegisterControl() {return attorneyRegisterControl;}
}
