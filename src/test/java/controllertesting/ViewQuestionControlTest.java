package controllertesting;

import adapter.controller.ViewQuestionControl;
import businessrule.inputboundary.ViewInputBoundary;
import businessrule.responsemodel.ViewResponseModel;
import businessrule.usecase.util.QuestionDisplayFormatter;

import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ViewQuestionControlTest {
    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static ViewResponseModel expectedResponse;

    public void setUpViewQuestionControl() {
        // Arrange
        Map<Integer, QuestionDisplayFormatter> questionMap = Collections.emptyMap(); // Sample empty map
        expectedResponse = new ViewResponseModel(USER_ID, USER_NAME, questionMap); // Initializing with suitable values
    }

    @Test
    public void testViewQuestion() {
        setUpViewQuestionControl();

        ViewInputBoundary mockInputBoundary = mock(ViewInputBoundary.class);
        when(mockInputBoundary.viewQuestion(any(ViewRequestModel.class))).thenReturn(expectedResponse);

        ViewQuestionControl control = new ViewQuestionControl(mockInputBoundary);

        // Act
        ViewResponseModel actualResponse = control.viewQuestion(USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).viewQuestion(any(ViewRequestModel.class));
    }
}
