package controllertesting;

import adapter.controller.CloseQuestionControl;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class CloseQuestionControlTest {
    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static HomePageResponseModel expectedResponse;

    public void setUpCloseQuestionControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    @Test
    public void testCloseQuestion() {
        setUpCloseQuestionControl();

        CloseInputBoundary mockInputBoundary = mock(CloseInputBoundary.class);
        when(mockInputBoundary.closeQuestion(any(CloseRequestModel.class))).thenReturn(expectedResponse);

        CloseQuestionControl control = new CloseQuestionControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.closeQuestion(QUESTION_ID, USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).closeQuestion(any(CloseRequestModel.class));
    }
}
