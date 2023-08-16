package controllertesting;

import adapters.controllers.RateControl;
import usecases.session.SessionManager;
import usecases.session.UserSession;
import usecases.inputboundary.RateInputBoundary;
import usecases.requests.RateRequestModel;
import usecases.responses.UserResponseModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the RateControl class.
 */
public class RateControlTest {
    private static final int RATE = 5;
    private static final int USER_ID = 11123456;
    private static final int QUESTION_ID = 333333333;
    private static UserResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing RateControl.
     */
    public void setUpRateControl() {
        // Arrange
        expectedResponse = new UserResponseModel(USER_ID, "SampleUser", "SampleType");

        // Set up user session if needed
        UserResponseModel userResponseModel = new UserResponseModel(USER_ID, "SampleUser", "SampleType");
        UserSession userSession = new UserSession(userResponseModel);
        SessionManager.setSession(userSession);
    }

    /**
     * Tests the rateAnswer method of RateControl.
     */
    @Test
    public void testRateAnswer() {
        setUpRateControl();

        RateInputBoundary mockInputBoundary = mock(RateInputBoundary.class);
        when(mockInputBoundary.rateAnswer(any(RateRequestModel.class))).thenReturn(expectedResponse);

        RateControl control = new RateControl(mockInputBoundary);

        // Act
        UserResponseModel actualResponse = control.rateAnswer(RATE, QUESTION_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).rateAnswer(any(RateRequestModel.class));
    }
}
