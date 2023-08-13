package businessrule;

import driver.screen.BaseUI;

public class UIFactory {
    public enum UIType {
        WELCOME_UI,
        REGISTER_UI,
        LOGIN_UI,
        HOME_PAGE,
        ASK_QUESTION_UI,
    }

    public static BaseUI createUI(UIType uiType) {
        switch (uiType) {
            case ASK_QUESTION_UI:
                return new AskQuestionUI();
            case ATTORNEY_HOME_PAGE:
                return new AttorneyHomePage();
            case CLIENT_HOME_PAGE:
                return new ClientHomePage();
            case QUESTION_LIST_UI:
                return new QuestionListUI();
            case THE_QUESTION_CLOSE_UI:
                return new QuestionCloseUI();
            case THE_QUESTION_OPEN_UI:
                return new QuestionOpenUI();
            case THE_QUESTION_ATTORNEY_UI:
                return new QuestionAttorneyUI();
            default:
                throw new IllegalArgumentException("Invalid UI type provided.");
        }
    }

}
