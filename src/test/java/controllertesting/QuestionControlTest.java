package controllertesting;

import adapter.controller.QuestionControl;
import businessrule.SessionManager;
import businessrule.UserSession;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;

import businessrule.responsemodel.UserResponseModel;
import businessrule.usecase.util.PostDisplayFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionControlTest {
    private static int USER_ID = 11234567;
    private static int QUESTION_ID = 333333333;
    private static String USER_NAME = "SampleUser";
    private static String TITLE = "SampleTitle";
    private static String TYPE = "SampleType";

    private static UserResponseModel expectedUserResponse;

    public void setUpQuestionControl() {
        // Arrange
        UserResponseModel userResponseModel = new UserResponseModel(USER_ID, USER_NAME, TYPE);
        UserSession userSession = new UserSession(userResponseModel);
        SessionManager.setSession(userSession); // Set the user session

        LocalDate deadline = LocalDate.now().plusDays(5);
        boolean isClose = false;
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>(); // Assuming suitable values or empty

        int userId = 42; // Set the expected userId
        String userName = "SampleUser";
        String userType = "SampleType";

        expectedUserResponse = new UserResponseModel(userId, userName, userType);
    }

    @Test
    public void testCreateQuestion() {
        setUpQuestionControl();
        QuestionInputBoundary mockInputBoundary = mock(QuestionInputBoundary.class);
        when(mockInputBoundary.createQuestion(any(QuestionRequestModel.class))).thenReturn(expectedUserResponse);

        QuestionControl control = new QuestionControl(mockInputBoundary);

        // Sample input parameters
        String questionCategory = "SampleCategory";
        LocalDate createAt = LocalDate.now();
        LocalDate legalDeadline = LocalDate.now().plusDays(10);

        // Act
        UserResponseModel actualResponse = control.createQuestion(questionCategory, TITLE, createAt, legalDeadline);

        // Assert
        assertEquals(expectedUserResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createQuestion(any(QuestionRequestModel.class));
    }
}