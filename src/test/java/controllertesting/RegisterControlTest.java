package controllertesting;

import adapters.controllers.RegisterControl;
import usecases.inputboundary.UserRegisterInputBoundary;
import usecases.requests.ClientRegistrationData;
import usecases.requests.RegistrationData;
import usecases.responses.BaseResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ClientRegisterControl class.
 */
public class RegisterControlTest {
    private static final String USER_NAME = "SampleUser";
    private static final String EMAIL = "sample@example.com";
    private static final String PASSWORD1 = "test password";
    private static final String PASSWORD2 = "test password";
    private static final String STATE_ABB = "CA";
    private static final String POSTAL_CODE = "90001";
    private static final String ETHNICITY = "SampleEthnicity";
    private static final int AGE = 25;
    private static final String GENDER = "Male";
    private static final String MARITAL_STATUS = "Single";
    private static final int NUMBER_OF_HOUSEHOLD = 3;
    private static final float ANNUAL_INCOME = 60000.0f;

    private static BaseResponseModel expectedResponse;

    /**
     * Sets up the needed resources for testing ClientRegisterControl.
     */
    public void setUpClientRegisterControl() {
        // Arrange
        expectedResponse = new BaseResponseModel();
    }

    /**
     * Tests the create method of ClientRegisterControl.
     */
    @Test
    public void testCreateClientRegistration() {
        setUpClientRegisterControl();

        UserRegisterInputBoundary mockInputBoundary = mock(UserRegisterInputBoundary.class);
        when(mockInputBoundary.create(any(RegistrationData.class))).thenReturn(expectedResponse);

        RegisterControl control = new RegisterControl(mockInputBoundary);

        RegistrationData data = new RegistrationData(USER_NAME, EMAIL, PASSWORD1, PASSWORD2, STATE_ABB, POSTAL_CODE);
        ClientRegistrationData inputData = new ClientRegistrationData.Builder(data)
                .age(AGE)
                .annualIncome(ANNUAL_INCOME)
                .gender(GENDER)
                .maritalStatus(MARITAL_STATUS)
                .numberOfHousehold(NUMBER_OF_HOUSEHOLD)
                .ethnicity(ETHNICITY)
                .build();

        // Act
        BaseResponseModel actualResponse = control.create(inputData);

        // Assert
        assertNotNull(actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).create(any(RegistrationData.class));
    }
}