package controllertesting;

import adapters.controllers.SelectQuestionControl;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.inputboundary.SelectInputBoundary;
import usecases.requests.SelectRequestModel;
import usecases.responses.TheQuestionResponseModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the SelectQuestionControl class.
 */
public class SelectQuestionControlTest {
    private static final int QUESTION_ID = 323456783;
    private static final int USER_ID = 11233456;
    private static final String USER_NAME = "SampleUser";
    private static final String USER_TYPE = "SampleType";
    private static final String QUESTION_TITLE = "Sample title";
    private static final String QUESTION_TYPE = "sample question type";
    private static TheQuestionResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing SelectQuestionControl.
     */
    public void setUpSelectQuestionControl() {
        // Arrange
        expectedResponse = new TheQuestionResponseModel(USER_ID, USER_NAME, USER_TYPE, QUESTION_ID, QUESTION_TITLE, QUESTION_TYPE, null, false, null);

        // Set up user session if needed
        ViewResponseModel userResponseModel = new ViewResponseModel(USER_ID, "SampleUser", "SampleType", null);
        UserSession userSession = new UserSession(userResponseModel);
        SessionManager.setSession(userSession);
    }

    /**
     * Tests the selectQuestion method of SelectQuestionControl.
     */
    @Test
    public void testSelectQuestion() {
        setUpSelectQuestionControl();

        SelectInputBoundary mockInputBoundary = mock(SelectInputBoundary.class);
        when(mockInputBoundary.selectQuestion(any(SelectRequestModel.class))).thenReturn(expectedResponse);

        SelectQuestionControl control = new SelectQuestionControl(mockInputBoundary);

        // Act
        TheQuestionResponseModel actualResponse = control.selectQuestion(QUESTION_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).selectQuestion(any(SelectRequestModel.class));
    }
}

