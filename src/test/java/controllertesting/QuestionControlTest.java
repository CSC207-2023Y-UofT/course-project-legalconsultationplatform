package controllertesting;

import adapters.controllers.QuestionControl;
import usecases.responses.ViewResponseModel;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.inputboundary.QuestionInputBoundary;
import usecases.requests.QuestionRequestModel;
import usecases.responses.TheQuestionResponseModel;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the QuestionControl class.
 */
public class QuestionControlTest {
    private static final int USER_ID = 11234567;
    private static final int QUESTION_ID = 333333333;
    private static final String USER_NAME = "SampleUser";
    private static final String TITLE = "SampleTitle";
    private static final String TYPE = "SampleType";
    private static TheQuestionResponseModel expectedUserResponse;

    /**
     * Sets up the necessary resources for testing QuestionControl.
     */
    public void setUpQuestionControl(){
        // Arrange
        ViewResponseModel userResponseModel = new ViewResponseModel(USER_ID, USER_NAME, TYPE, null);
        UserSession userSession = new UserSession(userResponseModel);
        SessionManager.setSession(userSession);

        expectedUserResponse = new TheQuestionResponseModel(USER_ID, USER_NAME, "Client", QUESTION_ID, TITLE, TYPE, null, false, null);

    }

    /**
     * Tests the createQuestion method of QuestionControl.
     */
    @Test
    public void testCreateQuestion() {
        setUpQuestionControl();
        QuestionInputBoundary mockInputBoundary = mock(QuestionInputBoundary.class);
        when(mockInputBoundary.createQuestion(any(QuestionRequestModel.class))).thenReturn(expectedUserResponse);

        when(mockInputBoundary.createQuestion(any(QuestionRequestModel.class))).thenReturn(expectedUserResponse);
        QuestionControl control = new QuestionControl(mockInputBoundary);

        // Sample input parameters
        String questionCategory = "SampleCategory";
        LocalDate createAt = LocalDate.now();
        LocalDate legalDeadline = LocalDate.now().plusDays(10);

        // Act
        TheQuestionResponseModel actualResponse = control.createQuestion(questionCategory, TITLE, createAt, legalDeadline);

        // Assert
        assertEquals(expectedUserResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createQuestion(any(QuestionRequestModel.class));
    }
}