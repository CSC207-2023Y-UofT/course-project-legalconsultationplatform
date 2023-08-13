package controllertesting;

import adapter.controller.CloseQuestionControl;
import businessrule.inputboundary.CloseInputBoundary;
import businessrule.requestmodel.CloseRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the CloseQuestionControl class.
 */
public class CloseQuestionControlTest {

    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static HomePageResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing CloseQuestionControl.
     */
    public void setUpCloseQuestionControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    /**
     * Tests the closeQuestion method of CloseQuestionControl.
     */
    @Test
    public void testCloseQuestion() {
        setUpCloseQuestionControl();

        // Create a mock for CloseInputBoundary
        CloseInputBoundary mockInputBoundary = mock(CloseInputBoundary.class);
        when(mockInputBoundary.closeQuestion(any(CloseRequestModel.class))).thenReturn(expectedResponse);

        // Create an instance of CloseQuestionControl
        CloseQuestionControl control = new CloseQuestionControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.closeQuestion(QUESTION_ID, USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).closeQuestion(any(CloseRequestModel.class));
    }
}

