package controllertesting;

import adapter.controller.UserLoginControl;
import businessrule.inputboundary.UserLoginInputBoundary;
import businessrule.requestmodel.UserLoginRequestModel;

import businessrule.responsemodel.UserResponseModel;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class UserLoginControlTest {
    private static final int USER_ID = 11234567;
    private static final String PASSWORD = "test password";
    private static UserResponseModel expectedResponse;

    public void setUpUserLoginControl() {
        // Arrange
        expectedResponse = new UserResponseModel(USER_ID, "SampleUser", "SampleType");
    }

    @Test
    public void testLogin() {
        setUpUserLoginControl();

        UserLoginInputBoundary mockInputBoundary = mock(UserLoginInputBoundary.class);
        when(mockInputBoundary.login(any(UserLoginRequestModel.class))).thenReturn(expectedResponse);

        UserLoginControl control = new UserLoginControl(mockInputBoundary);

        // Act
        UserResponseModel actualResponse = control.login(USER_ID, PASSWORD);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).login(any(UserLoginRequestModel.class));
    }
}

