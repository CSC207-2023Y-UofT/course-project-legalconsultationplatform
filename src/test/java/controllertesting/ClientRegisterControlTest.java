package controllertesting;

import adapter.controller.ClientRegisterControl;
import businessrule.responsemodel.RegisterResponseModel;

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

    private static RegisterResponseModel expectedResponse;

    public void setUpClientRegisterControl() {
        // Arrange
        expectedResponse = new RegisterResponseModel();
    }

    @Test
    public void testCreateClientRegistration() {
        setUpClientRegisterControl();

        ClientRegisterInputBoundary mockInputBoundary = mock(ClientRegisterInputBoundary.class);
        when(mockInputBoundary.create(any(ClientRegisterRequestModel.class))).thenReturn(expectedResponse);

        ClientRegisterControl control = new ClientRegisterControl(mockInputBoundary);

        // Act
        RegisterResponseModel actualResponse = control.create(USER_NAME, EMAIL, PASSWORD1, PASSWORD2, STATE_ABB,
                POSTAL_CODE, ETHNICITY, AGE, GENDER, MARITAL_STATUS, NUMBER_OF_HOUSEHOLD, ANNUAL_INCOME);

        // Assert
        assertNotNull(actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).create(any(ClientRegisterRequestModel.class));
    }
}