package adapters.controllers;

/**
 * This class represents that holds instances of various control classes.
 * It is responsible for managing different aspects of the application.
 */
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
    ViewQuestionControl recommendationControl;

    /**
     * Constructor to initialize the ControlContainer with instances of various control classes.
     *
     * @param clientRegisterControl The control class for client registration.
     * @param closeQuestionControl The control class for closing questions.
     * @param postControl The control class for handling posts.
     * @param questionControl The control class for question management.
     * @param rateControl The control class for rating questions.
     * @param selectQuestionControl The control class for selecting questions.
     * @param userLoginControl The control class for user login.
     * @param viewQuestionControl The control class for viewing questions.
     * @param browseQuestionControl The control class for browsing questions.
     * @param recommendationControl The control class for viewing recommended questions.
     */
    public ControlContainer(ClientRegisterControl clientRegisterControl, CloseQuestionControl closeQuestionControl,
                            PostControl postControl, QuestionControl questionControl, RateControl rateControl,
                            SelectQuestionControl selectQuestionControl, UserLoginControl userLoginControl,
                            ViewQuestionControl viewQuestionControl, ViewQuestionControl browseQuestionControl,
                            ViewQuestionControl recommendationControl) {
        this.clientRegisterControl = clientRegisterControl;
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

    /**
     * Get the control class for client registration.
     *
     * @return The client registration control class.
     */
    public ClientRegisterControl getClientRegisterControl() {
        return clientRegisterControl;
    }

    /**
     * Get the control class for closing questions.
     *
     * @return The close question control class.
     */
    public CloseQuestionControl getCloseQuestionControl() {
        return closeQuestionControl;
    }

    /**
     * Get the control class for handling posts.
     *
     * @return The post control class.
     */
    public PostControl getPostControl() {
        return postControl;
    }

    /**
     * Get the control class for question management.
     *
     * @return The question control class.
     */
    public QuestionControl getQuestionControl() {
        return questionControl;
    }

    /**
     * Get the control class for rating questions.
     *
     * @return The rate control class.
     */
    public RateControl getRateControl() {
        return rateControl;
    }

    /**
     * Get the control class for selecting questions.
     *
     * @return The select question control class.
     */
    public SelectQuestionControl getSelectQuestionControl() {
        return selectQuestionControl;
    }

    /**
     * Get the control class for user login.
     *
     * @return The user login control class.
     */
    public UserLoginControl getUserLoginControl() {
        return userLoginControl;
    }

    /**
     * Get the control class for viewing questions.
     *
     * @return The view question control class.
     */
    public ViewQuestionControl getViewQuestionControl() {
        return viewQuestionControl;
    }

    public ViewQuestionControl getBrowseQuestionControl() {return browseQuestionControl;}

    public ViewQuestionControl getRecommendationControl() {return recommendationControl;}
}
