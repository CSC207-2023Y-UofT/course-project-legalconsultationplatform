package controllertesting;

import adapter.controller.QuestionControl;
import businessrule.inputboundary.QuestionInputBoundary;
import businessrule.requestmodel.QuestionRequestModel;
import businessrule.responsemodel.TheQuestionResponseModel;

import businessrule.usecase.util.PostDisplayFormatter;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class QuestionControlTest {
    private static int USER_ID = 11234567;
    private static int QUESTION_ID = 333333333;
    private static String USER_NAME = "SampleUser";
    private static String TITLE = "SampleTitle";
    private static String TYPE = "SampleType";

    private static TheQuestionResponseModel expectedResponse;

    public void setUpQuestionControl(){
        // Arrange
        LocalDate deadline = LocalDate.now().plusDays(5);
        boolean isClose = false;
        Map<Integer, PostDisplayFormatter> postMap = new HashMap<>(); // Assuming suitable values or empty

         expectedResponse = new TheQuestionResponseModel(USER_ID, QUESTION_ID, USER_NAME, TITLE, TYPE, deadline, isClose, postMap);
    }

    @Test
    public void testCreateQuestion() {
        setUpQuestionControl();
        QuestionInputBoundary mockInputBoundary = mock(QuestionInputBoundary.class);
        when(mockInputBoundary.createQuestion(any(QuestionRequestModel.class))).thenReturn(expectedResponse);

        QuestionControl control = new QuestionControl(mockInputBoundary);

        // Sample input parameters
        String questionCategory = "SampleCategory";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 42;
        LocalDate legalDeadline = LocalDate.now().plusDays(10);

        // Act
        TheQuestionResponseModel actualResponse = control.createQuestion(questionCategory, TITLE, createAt, askedByClient, legalDeadline);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createQuestion(any(QuestionRequestModel.class));
    }
}