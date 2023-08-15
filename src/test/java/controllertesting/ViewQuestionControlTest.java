package controllertesting;

import adapter.controller.ViewQuestionControl;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ViewQuestionControl class.
 */
public class ViewQuestionControlTest {
    private static final int USER_ID = 11234567;
    private static final String USER_NAME = "SampleUser";
    private static final String USER_TYPE = "SampleType";
    private static ViewResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing ViewQuestionControl.
     */
    public void setUpViewQuestionControl() {
        // Arrange
        expectedResponse = new ViewResponseModel(USER_ID, USER_NAME, USER_TYPE, null);
    }

    /**
     * Tests the viewQuestion method of ViewQuestionControl.
     */
    @Test
    public void testViewQuestion() {
        setUpViewQuestionControl();

        ViewInputBoundary mockInputBoundary = mock(ViewInputBoundary.class);
        when(mockInputBoundary.viewQuestion()).thenReturn(expectedResponse);

        ViewQuestionControl control = new ViewQuestionControl(mockInputBoundary);

        // Act
        ViewResponseModel actualResponse = control.viewQuestion();

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).viewQuestion();
    }
}
