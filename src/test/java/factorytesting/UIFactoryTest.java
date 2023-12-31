package factorytesting;

import infrastructure.screens.UIFactory;
import usecases.responses.BaseResponseModel;
import usecases.responses.TheQuestionResponseModel;
import usecases.responses.UserResponseModel;
import usecases.responses.ViewResponseModel;
import usecases.utils.BuilderService;
import usecases.dto.PostDisplay;
import usecases.dto.QuestionDisplay;
import infrastructure.screens.*;
import infrastructure.screens.utils.UIManager;
import entities.Question;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import javax.swing.*;
import java.awt.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import static org.junit.jupiter.api.Assertions.assertEquals;

class UIFactoryTest {

    private final int USER_ID = 24567893;
    private final int QUESTION_ID = 324724768;
    private final String USER_NAME = "test client";
    private final String TITLE = "test title";
    private final String QUESTION_TYPE = "test type";
    private UserResponseModel userResponseModel;
    private BaseResponseModel baseResponseModel;
    private TheQuestionResponseModel theQuestionResponseModel;
    private ViewResponseModel viewResponseModel;
    private UIManager uiManager;
    private Map<Integer, PostDisplay> postMap;
    private Map<Integer, QuestionDisplay> questionMap;

    @BeforeEach
    void setUpUIFactory() {
        baseResponseModel = new BaseResponseModel();
        String USER_TYPE = "Client";
        userResponseModel = new UserResponseModel(USER_ID, USER_NAME, USER_TYPE);
        JPanel testScreen = new JPanel();
        CardLayout testCardLayout = new CardLayout();
        uiManager = new UIManager(testScreen, testCardLayout);
        Question question = new Question(QUESTION_ID, QUESTION_TYPE, TITLE, LocalDate.now(), USER_ID, LocalDate.now());
        String POST_TEXT = "test text";
        PostDisplay post = new PostDisplay(POST_TEXT, USER_TYPE, USER_NAME, LocalDate.now());

        postMap = new HashMap<>();
        postMap.put(1, post);
        questionMap = new HashMap<>();
        theQuestionResponseModel = BuilderService.getInstance().constructTheQuestionResponse(question, userResponseModel, postMap);
        viewResponseModel = BuilderService.getInstance().constructViewResponse(userResponseModel, questionMap);


    }

    @Test
    void testGetWelcomeUI() {
        setUpUIFactory();
        WelcomeUI expectedUI = new WelcomeUI(uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.WELCOME_UI, uiManager, baseResponseModel).getClass());
    }
    @Test
    void testGetClientRegisterUI() {
        setUpUIFactory();
        ClientRegisterUI expectedUI = new ClientRegisterUI(uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.CLIENT_REGISTER_UI, uiManager, baseResponseModel).getClass());
    }
    @Test
    void testGetAttorneyRegisterUI(){
        setUpUIFactory();
        AttorneyRegisterUI expectedUI = new AttorneyRegisterUI(uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.ATTORNEY_REGISTER_UI, uiManager, baseResponseModel).getClass());
    }
    @Test
    void testGetLoginUI() {
        setUpUIFactory();
        LoginUI expectedUI = new LoginUI(uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.LOGIN_UI, uiManager, baseResponseModel).getClass());
    }

    @Test
    void testGetHomePageUI() {
        setUpUIFactory();
        HomePageUI expectedUI = new ClientHomePageUI(USER_NAME, USER_ID, uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.HOME_PAGE, uiManager, userResponseModel).getClass());
    }

    @Test
    void testAskQuestionUI() {
        setUpUIFactory();
        AskQuestionUI expectedUI = new AskQuestionUI(USER_NAME, USER_ID, uiManager);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.ASK_QUESTION_UI, uiManager, theQuestionResponseModel).getClass());
    }

    @Test
    void testQuestionListUI() {
        setUpUIFactory();
        QuestionListUI expectedUI = new QuestionListUI(USER_NAME, USER_ID, uiManager, questionMap);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.QUESTION_LIST_UI, uiManager, viewResponseModel).getClass());
    }

    @Test
    void testQuestionUI() {
        setUpUIFactory();
        QuestionOpenClientUI expectedUI = new QuestionOpenClientUI(USER_NAME, USER_ID, uiManager, QUESTION_ID, TITLE, QUESTION_TYPE, LocalDate.now(), postMap);
        assertEquals(expectedUI.getClass(), UIFactory.getUI(UIFactory.UIType.QUESTION_UI, uiManager, theQuestionResponseModel).getClass());
    }
}