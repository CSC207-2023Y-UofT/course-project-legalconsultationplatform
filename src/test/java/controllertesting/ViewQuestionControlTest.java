package controllertesting;

import adapter.controller.ViewQuestionControl;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.requestmodel.ViewRequestModel;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ViewQuestionControl class.
 */
public class ViewQuestionControlTest {

    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static ViewResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing ViewQuestionControl.
     */
    public void setUpViewQuestionControl() {
        // Arrange
        Map<Integer, QuestionDisplayFormatter> questionMap = Collections.emptyMap(); // Sample empty map
        expectedResponse = new ViewResponseModel(USER_ID, USER_NAME, questionMap); // Initializing with suitable values
    }

    /**
     * Tests the viewQuestion method of ViewQuestionControl.
     */
    @Test
    public void testViewQuestion() {
        setUpViewQuestionControl();

        // Create a mock for ViewInputBoundary
        ViewInputBoundary mockInputBoundary = mock(ViewInputBoundary.class);
        when(mockInputBoundary.viewQuestion(any(ViewRequestModel.class))).thenReturn(expectedResponse);

        // Create an instance of ViewQuestionControl
        ViewQuestionControl control = new ViewQuestionControl(mockInputBoundary);

        // Act
        ViewResponseModel actualResponse = control.viewQuestion(USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).viewQuestion(any(ViewRequestModel.class));
    }
}
