package controllertesting;

import adapters.controllers.PostControl;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.inputboundary.PostInputBoundary;
import usecases.requests.PostRequestModel;
import usecases.responses.TheQuestionResponseModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the PostControl class.
 */
public class PostControlTest {
    private static final int USER_ID = 11234567;
    private static final String USER_NAME = "SampleUser";
    private static final String USER_TYPE = "SampleType";
    private static final int QUESTION_ID = 333333333;
    private static final String QUESTION_TITLE = "Sample title";
    private static final String QUESTION_TYPE = "sample question type";
    private static final String POST_TEXT = "Sample Post Text";

    private static TheQuestionResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing PostControl.
     */
    public void setUpPostControl() {
        // Arrange
        expectedResponse = new TheQuestionResponseModel(USER_ID, USER_NAME, USER_TYPE, QUESTION_ID, QUESTION_TITLE, QUESTION_TYPE, null, false, null);
    }

    /**
     * Tests the createPost method of PostControl.
     */
    @Test
    public void testCreatePost() {
        setUpPostControl();

        PostInputBoundary mockInputBoundary = mock(PostInputBoundary.class);
        when(mockInputBoundary.createPost(any(PostRequestModel.class))).thenReturn(expectedResponse);

        PostControl control = new PostControl(mockInputBoundary);
        ViewResponseModel userResponseModel = new ViewResponseModel(USER_ID, USER_NAME, "attorney", null);
        UserSession session = new UserSession(userResponseModel);
        SessionManager.setSession(session);

        // Act
        TheQuestionResponseModel actualResponse = control.createPost(QUESTION_ID, POST_TEXT);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createPost(any(PostRequestModel.class));
    }
}