package infrastructure.screens;

import infrastructure.screens.utils.UIManager;
import usecases.responses.BaseResponseModel;
import usecases.responses.TheQuestionResponseModel;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;
import usecases.dto.PostDisplay;
import java.util.HashMap;
import java.util.Map;

/**
 * This class represents a factory that create UI based on specific needs and situations.
 */
public class UIFactory {
    private static final String CLIENT_TYPE = "Client";
    private static final String ATTORNEY_TYPE = "Attorney";
    private static final Map<UIType, BaseUI> uiCache = new HashMap<>();

    public enum UIType {
        WELCOME_UI,
        REGISTER_UI,
        LOGIN_UI,
        HOME_PAGE,
        ASK_QUESTION_UI,
        QUESTION_LIST_UI,
        QUESTION_UI
    }

    /**
     *
     * @param uiType type of the target UI.
     * @param uiManager UIManager that contains screens, cardlayouts, and control containers.
     * @param userResponseModel a response model contains basic user information.
     * @return the specific UI needed.
     * @throws IllegalArgumentException when the UI type is invalid.
     */
    private static UserUI getUserUI(UIType uiType, UIManager uiManager, UserResponseModel userResponseModel) throws IllegalArgumentException {
        int userId = userResponseModel.getUserId();
        String userName = userResponseModel.getUserName();
        String userType = userResponseModel.getUserType();

        switch (uiType) {
            case HOME_PAGE:
                if (userType.equals(CLIENT_TYPE)) {
                    return new ClientHomePageUI(userName, userId, uiManager);
                } else if (userType.equals(ATTORNEY_TYPE)) {
                    return new AttorneyHomePageUI(userName, userId, uiManager);
                } else {
                    throw new IllegalArgumentException("Invalid User Type");
                }

            case ASK_QUESTION_UI:
                return new AskQuestionUI(userName, userId, uiManager);
            default:
                throw new IllegalArgumentException("Invalid UI Type");
        }
    }

    private static QuestionListUI getQuestionListUI(UIManager uiManager, ViewResponseModel viewResponseModel) {
        return new QuestionListUI(viewResponseModel.getUserName(), viewResponseModel.getUserId(), uiManager, viewResponseModel.getQuestionMap());
    }

    private static QuestionUI getQuestionUI(UIManager uiManager, TheQuestionResponseModel questionResponseModel) {
        String userType = questionResponseModel.getUserType();
        boolean isClose = questionResponseModel.isClose();
        Map<Integer, PostDisplay> postMap = questionResponseModel.getPostMap();
        if (isClose) {
            return new QuestionCloseUI(questionResponseModel.getUserName(), questionResponseModel.getUserId(),
                    uiManager, questionResponseModel.getQuestionId(), questionResponseModel.getTitle(), questionResponseModel.getType(),
                    questionResponseModel.getDeadline(), questionResponseModel.getPostMap());
        } else if (userType.equals(ATTORNEY_TYPE)){
            return new QuestionOpenAttorneyUI(questionResponseModel.getUserName(),
                    questionResponseModel.getUserId(), uiManager, questionResponseModel.getQuestionId(),
                    questionResponseModel.getTitle(), questionResponseModel.getType(), questionResponseModel.getDeadline(),
                    questionResponseModel.getPostMap());
        } else if (postMap.isEmpty()) {
            return new QuestionNewUI(questionResponseModel.getUserName(), questionResponseModel.getUserId(), uiManager,
                    questionResponseModel.getQuestionId(), questionResponseModel.getTitle(),
                    questionResponseModel.getType(), questionResponseModel.getDeadline(), questionResponseModel.getPostMap());
        } else{
            return new QuestionOpenClientUI(questionResponseModel.getUserName(), questionResponseModel.getUserId(), uiManager,
                    questionResponseModel.getQuestionId(), questionResponseModel.getTitle(),
                    questionResponseModel.getType(), questionResponseModel.getDeadline(), questionResponseModel.getPostMap());
        }
    }

    private static BaseUI getBaseUI(UIType uiType, UIManager uiManager) {
        switch (uiType) {
            case WELCOME_UI:
                return new WelcomeUI(uiManager);

            case REGISTER_UI:
                return new RegisterUI(uiManager);

            case LOGIN_UI:
                return new LoginUI(uiManager);

                default:
                throw new IllegalArgumentException("Invalid UI Type");
        }
    }

    public static BaseUI getUI(UIType uiType, UIManager uiManager, BaseResponseModel responseModel) throws IllegalArgumentException {
        if (uiCache.containsKey(uiType)) {
            return uiCache.get(uiType);
        }

        BaseUI uiInstance;
        switch (uiType) {
            case WELCOME_UI:
            case REGISTER_UI:
            case LOGIN_UI:
                uiInstance = getBaseUI(uiType, uiManager);
                break;

            case HOME_PAGE:
            case ASK_QUESTION_UI:
                uiInstance = getUserUI(uiType, uiManager, (UserResponseModel) responseModel);
                break;

            case QUESTION_LIST_UI:
                return getQuestionListUI(uiManager, (ViewResponseModel) responseModel);

            case QUESTION_UI:
                return getQuestionUI(uiManager, (TheQuestionResponseModel) responseModel);

            default:
                throw new IllegalArgumentException("Invalid UI Type");
        }

        uiCache.put(uiType, uiInstance);
        return uiInstance;
    }
    public static void clearUIFactory(){
        uiCache.clear();
    }
}

