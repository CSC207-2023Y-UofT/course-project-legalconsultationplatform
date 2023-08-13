package controllertesting;

import adapter.controller.UserLoginControl;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the UserLoginControl class.
 */
public class UserLoginControlTest {

    private static int USER_ID = 11234567;
    private static String PASSWORD = "test password";
    private static HomePageResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing UserLoginControl.
     */
    public void setUpUserLoginControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    /**
     * Tests the login method of UserLoginControl.
     */
    @Test
    public void testLogin() {
        setUpUserLoginControl();

        // Create a mock for UserLoginInputBoundary
        UserLoginInputBoundary mockInputBoundary = mock(UserLoginInputBoundary.class);
        when(mockInputBoundary.login(any(UserLoginRequestModel.class))).thenReturn(expectedResponse);

        // Create an instance of UserLoginControl
        UserLoginControl control = new UserLoginControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.login(USER_ID, PASSWORD);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).login(any(UserLoginRequestModel.class));
    }
}