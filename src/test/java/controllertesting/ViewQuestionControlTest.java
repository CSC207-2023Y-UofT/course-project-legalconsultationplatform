package controllertesting;

import adapter.controller.ViewQuestionControl;
import businessrule.inputboundary.ViewInputBoundary;

import businessrule.responsemodel.UserResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ViewQuestionControlTest {
    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static UserResponseModel expectedResponse;

    public void setUpViewQuestionControl() {
        // Arrange
        expectedResponse = new UserResponseModel(USER_ID, USER_NAME, "SampleType"); // Initializing with suitable values
    }

    @Test
    public void testViewQuestion() {
        setUpViewQuestionControl();

        ViewInputBoundary mockInputBoundary = mock(ViewInputBoundary.class);
        when(mockInputBoundary.viewQuestion()).thenReturn(expectedResponse);

        ViewQuestionControl control = new ViewQuestionControl(mockInputBoundary);

        // Act
        UserResponseModel actualResponse = control.viewQuestion();

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).viewQuestion();
    }
}
