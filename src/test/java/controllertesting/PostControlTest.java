package controllertesting;

import adapter.controller.PostControl;
import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;
import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.PostDisplayFormatter;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostControlTest {
    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static String USER_TYPE = "SampleType";
    private static int QUESTION_ID = 333333333;
    private static String POST_TEXT = "Sample Post Text";
    private static final Map<Integer, PostDisplayFormatter> POST_MAP = new HashMap<>();

    private static TheQuestionResponseModel expectedResponse;

    public void setUpPostControl() {
        // Arrange
        expectedResponse =
        = new UserResponseModel(USER_ID, USER_NAME, USER_TYPE).toQuestionResponseBuilder()
    }

    @Test
    public void testCreatePost() {
        setUpPostControl();

        PostInputBoundary mockInputBoundary = mock(PostInputBoundary.class);
        when(mockInputBoundary.createPost(any(PostRequestModel.class))).thenReturn(expectedResponse);

        PostControl control = new PostControl(mockInputBoundary);
        UserResponseModel userResponseModel = new UserResponseModel(USER_ID, USER_NAME, "attorney");
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        // Act
        UserResponseModel actualResponse = control.createPost(QUESTION_ID, POST_TEXT);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createPost(any(PostRequestModel.class));
    }
}