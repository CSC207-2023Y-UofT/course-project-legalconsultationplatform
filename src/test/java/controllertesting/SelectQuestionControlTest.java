package controllertesting;

import adapter.controller.SelectQuestionControl;
import businessrule.inputboundary.SelectInputBoundary;
import businessrule.requestmodel.SelectRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class SelectQuestionControlTest {
    private static int QUESTION_ID = 333333333;
    private static int USER_ID = 11234567;
    private static TheQuestionResponseModel expectedResponse;

    public void setUpSelectQuestionControl() {
        // Arrange
        expectedResponse = new TheQuestionResponseModel(USER_ID, QUESTION_ID, "SampleUser", "SampleTitle",
                "SampleType", null, false, null); // Assuming suitable constructor or setters
    }

    @Test
    public void testSelectQuestion() {
        setUpSelectQuestionControl();

        SelectInputBoundary mockInputBoundary = mock(SelectInputBoundary.class);
        when(mockInputBoundary.selectQuestion(any(SelectRequestModel.class))).thenReturn(expectedResponse);

        SelectQuestionControl control = new SelectQuestionControl(mockInputBoundary);

        // Act
        TheQuestionResponseModel actualResponse = control.selectQuestion(QUESTION_ID, USER_ID);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).selectQuestion(any(SelectRequestModel.class));
    }
}
