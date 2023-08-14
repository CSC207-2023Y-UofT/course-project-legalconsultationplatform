package controllertesting;

import adapter.controller.CloseQuestionControl;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.UserResponseModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CloseQuestionControlTest {
    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static String USERNAME = "SampleUser";
    private static String USER_TYPE = "Client";
    private static UserResponseModel expectedResponse;

    public void setUpCloseQuestionControl() {
        expectedResponse = new UserResponseModel(USER_ID, USERNAME, USER_TYPE);
    }

    @Test
    public void testCloseQuestion() {
        setUpCloseQuestionControl();

        CloseInputBoundary mockInputBoundary = mock(CloseInputBoundary.class);
        when(mockInputBoundary.closeQuestion(any(CloseRequestModel.class))).thenReturn(expectedResponse);

        CloseQuestionControl control = new CloseQuestionControl(mockInputBoundary);

        // Act
        UserResponseModel actualResponse = control.closeQuestion(QUESTION_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).closeQuestion(any(CloseRequestModel.class));
    }
}