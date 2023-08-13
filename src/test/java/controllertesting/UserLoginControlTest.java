package controllertesting;

import adapter.controller.UserLoginControl;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserLoginControlTest {
    private static int USER_ID = 11234567;
    private static String PASSWORD = "test password";
    private static HomePageResponseModel expectedResponse;

    public void setUpUserLoginControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, "SampleUser", "SampleType"); // Assuming suitable constructor
    }

    @Test
    public void testLogin() {
        setUpUserLoginControl();

        UserLoginInputBoundary mockInputBoundary = mock(UserLoginInputBoundary.class);
        when(mockInputBoundary.login(any(UserLoginRequestModel.class))).thenReturn(expectedResponse);

        UserLoginControl control = new UserLoginControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.login(USER_ID, PASSWORD);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).login(any(UserLoginRequestModel.class));
    }
}
