package controllertesting;

import adapter.controller.ClientRegisterControl;
import businessrule.inputboundary.UserRegisterInputBoundary;
import businessrule.requestmodel.RegistrationData;
import businessrule.responsemodel.BaseResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

public class ClientRegisterControlTest {
    private static String USER_NAME = "SampleUser";
    private static String EMAIL = "sample@example.com";
    private static String PASSWORD1 = "test password";
    private static String PASSWORD2 = "test password";
    private static String STATE_ABB = "CA";
    private static String POSTAL_CODE = "90001";
    private static String ETHNICITY = "SampleEthnicity";
    private static int AGE = 25;
    private static String GENDER = "Male";
    private static String MARITAL_STATUS = "Single";
    private static int NUMBER_OF_HOUSEHOLD = 3;
    private static float ANNUAL_INCOME = 60000.0f;

    private static BaseResponseModel expectedResponse;

    public void setUpClientRegisterControl() {
        // Arrange
        expectedResponse = new BaseResponseModel();
    }

    @Test
    public void testCreateClientRegistration() {
        setUpClientRegisterControl();

        UserRegisterInputBoundary mockInputBoundary = mock(UserRegisterInputBoundary.class);
        when(mockInputBoundary.create(any(RegistrationData.class))).thenReturn(expectedResponse);

        ClientRegisterControl control = new ClientRegisterControl(mockInputBoundary);

        // Act
        BaseResponseModel actualResponse = control.create(USER_NAME, EMAIL, PASSWORD1, PASSWORD2, STATE_ABB,
                POSTAL_CODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        // Assert
        assertNotNull(actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).create(any(RegistrationData.class));
    }
}