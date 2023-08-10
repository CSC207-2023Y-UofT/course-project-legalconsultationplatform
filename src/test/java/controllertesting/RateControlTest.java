package controllertesting;

import adapter.controller.RateControl;
import businessrule.inputboundary.RateInputBoundary;
import businessrule.requestmodel.RateRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class RateControlTest {
    private static int RATE = 5;
    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static HomePageResponseModel expectedResponse;

    public void setUpRateControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    @Test
    public void testRateAnswer() {
        setUpRateControl();

        RateInputBoundary mockInputBoundary = mock(RateInputBoundary.class);
        when(mockInputBoundary.rateAnswer(any(RateRequestModel.class))).thenReturn(expectedResponse);

        RateControl control = new RateControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.rateAnswer(RATE, QUESTION_ID, USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).rateAnswer(any(RateRequestModel.class));
    }
}
