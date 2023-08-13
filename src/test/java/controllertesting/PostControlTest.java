package controllertesting;

import adapter.controller.PostControl;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class PostControlTest {
    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static String USER_TYPE = "SampleType";
    private static int QUESTION_ID = 333333333;
    private static String POST_TEXT = "Sample Post Text";

    private static HomePageResponseModel expectedResponse;

    public void setUpPostControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, USER_NAME, USER_TYPE);
    }

    @Test
    public void testCreatePost() {
        setUpPostControl();

        PostInputBoundary mockInputBoundary = mock(PostInputBoundary.class);
        when(mockInputBoundary.createPost(any(PostRequestModel.class))).thenReturn(expectedResponse);

        PostControl control = new PostControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.createPost(QUESTION_ID, USER_ID, POST_TEXT);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createPost(any(PostRequestModel.class));
    }
}