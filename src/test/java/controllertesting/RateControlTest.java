package controllertesting;

import adapter.controller.RateControl;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the RateControl class.
 */
public class RateControlTest {

    private static int RATE = 5;
    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static HomePageResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing RateControl.
     */
    public void setUpRateControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    /**
     * Tests the rateAnswer method of RateControl.
     */
    @Test
    public void testRateAnswer() {
        setUpRateControl();

        // Create a mock for RateInputBoundary
        RateInputBoundary mockInputBoundary = mock(RateInputBoundary.class);
        when(mockInputBoundary.rateAnswer(any(RateRequestModel.class))).thenReturn(expectedResponse);

        // Create an instance of RateControl
        RateControl control = new RateControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.rateAnswer(RATE, QUESTION_ID, USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).rateAnswer(any(RateRequestModel.class));
    }
}