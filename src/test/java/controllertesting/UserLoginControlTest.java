package controllertesting;

import adapters.controllers.UserLoginControl;
import usecases.inputboundary.UserLoginInputBoundary;
import usecases.requests.UserLoginRequestModel;

import org.junit.jupiter.api.Test;
import usecases.responses.ViewResponseModel;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the UserLoginControl class.
 */
public class UserLoginControlTest {
    private static final int USER_ID = 11234567;
    private static final String PASSWORD = "test password";
    private static ViewResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing UserLoginControl.
     */
    public void setUpUserLoginControl() {
        // Arrange
        expectedResponse = new ViewResponseModel(USER_ID, "SampleUser", "SampleType", null);
    }

    /**
     * Tests the login method of UserLoginControl.
     */
    @Test
    public void testLogin() {
        setUpUserLoginControl();

        UserLoginInputBoundary mockInputBoundary = mock(UserLoginInputBoundary.class);
        when(mockInputBoundary.login(any(UserLoginRequestModel.class))).thenReturn(expectedResponse);

        UserLoginControl control = new UserLoginControl(mockInputBoundary);

        // Act
        ViewResponseModel actualResponse = control.login(USER_ID, PASSWORD);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).login(any(UserLoginRequestModel.class));
    }
}

