package adapter.controller;

/**
 * This class represents a container for various control instances used within the application.
 * It holds references to different control objects that manage different aspects of the application's functionality.
 */
public class ControlContainer {
    BrowseQuestionControl browseQuestionControl;
    ClientRegisterControl clientRegisterControl;
    CloseQuestionControl closeQuestionControl;
    PostControl postControl;
    QuestionControl questionControl;
    RateControl rateControl;
    SelectQuestionControl selectQuestionControl;
    UserLoginControl userLoginControl;
    ViewQuestionControl viewQuestionControl;

    /**
     * Constructs a ControlContainer instance with the provided control objects.
     *
     * @param browseQuestionControl   The control object for browsing questions.
     * @param clientRegisterControl   The control object for client registration.
     * @param closeQuestionControl    The control object for closing questions.
     * @param postControl             The control object for managing posts.
     * @param questionControl         The control object for managing questions.
     * @param rateControl             The control object for rating questions.
     * @param selectQuestionControl   The control object for selecting questions.
     * @param userLoginControl        The control object for user login.
     * @param viewQuestionControl     The control object for viewing questions.
     */
    public ControlContainer(BrowseQuestionControl browseQuestionControl,
                            ClientRegisterControl clientRegisterControl,
                            CloseQuestionControl closeQuestionControl,
                            PostControl postControl, QuestionControl questionControl,
                            RateControl rateControl, SelectQuestionControl selectQuestionControl,
                            UserLoginControl userLoginControl, ViewQuestionControl viewQuestionControl) {
        this.browseQuestionControl = browseQuestionControl;
        this.clientRegisterControl = clientRegisterControl;
        this.closeQuestionControl = closeQuestionControl;
        this.postControl = postControl;
        this.questionControl = questionControl;
        this.rateControl = rateControl;
        this.selectQuestionControl = selectQuestionControl;
        this.userLoginControl = userLoginControl;
        this.viewQuestionControl = viewQuestionControl;
    }

    /**
     * Get the control object for browsing questions.
     *
     * @return The BrowseQuestionControl instance.
     */
    public BrowseQuestionControl getBrowseQuestionControl() {
        return browseQuestionControl;
    }

    /**
     * Get the control object for client registration.
     *
     * @return The ClientRegisterControl instance.
     */
    public ClientRegisterControl getClientRegisterControl() {
        return clientRegisterControl;
    }

    /**
     * Get the control object for closing questions.
     *
     * @return The CloseQuestionControl instance.
     */
    public CloseQuestionControl getCloseQuestionControl() {
        return closeQuestionControl;
    }

    /**
     * Get the control object for managing posts.
     *
     * @return The PostControl instance.
     */
    public PostControl getPostControl() {
        return postControl;
    }

    /**
     * Get the control object for managing questions.
     *
     * @return The QuestionControl instance.
     */
    public QuestionControl getQuestionControl() {
        return questionControl;
    }

    /**
     * Get the control object for rating questions.
     *
     * @return The RateControl instance.
     */
    public RateControl getRateControl() {
        return rateControl;
    }

    /**
     * Get the control object for selecting questions.
     *
     * @return The SelectQuestionControl instance.
     */
    public SelectQuestionControl getSelectQuestionControl() {
        return selectQuestionControl;
    }

    /**
     * Get the control object for user login.
     *
     * @return The UserLoginControl instance.
     */
    public UserLoginControl getUserLoginControl() {
        return userLoginControl;
    }

    /**
     * Get the control object for viewing questions.
     *
     * @return The ViewQuestionControl instance.
     */
    public ViewQuestionControl getViewQuestionControl() {
        return viewQuestionControl;
    }
}