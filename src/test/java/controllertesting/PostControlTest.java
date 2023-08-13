package controllertesting;

import adapter.controller.PostControl;
import businessrule.inputboundary.PostInputBoundary;
import businessrule.requestmodel.PostRequestModel;
import businessrule.responsemodel.HomePageResponseModel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the PostControl class.
 */
public class PostControlTest {

    private static int USER_ID = 11234567;
    private static String USER_NAME = "SampleUser";
    private static String USER_TYPE = "SampleType";
    private static int QUESTION_ID = 333333333;
    private static String POST_TEXT = "Sample Post Text";

    private static HomePageResponseModel expectedResponse;

    /**
     * Sets up the necessary resources for testing PostControl.
     */
    public void setUpPostControl() {
        // Arrange
        expectedResponse = new HomePageResponseModel(USER_ID, USER_NAME, USER_TYPE);
    }

    /**
     * Tests the createPost method of PostControl.
     */
    @Test
    public void testCreatePost() {
        setUpPostControl();

        // Create a mock for PostInputBoundary
        PostInputBoundary mockInputBoundary = mock(PostInputBoundary.class);
        when(mockInputBoundary.createPost(any(PostRequestModel.class))).thenReturn(expectedResponse);

        // Create an instance of PostControl
        PostControl control = new PostControl(mockInputBoundary);

        // Act
        HomePageResponseModel actualResponse = control.createPost(QUESTION_ID, USER_ID, POST_TEXT);

        // Assert
        assertEquals(expectedResponse, actualResponse);

        // Verify interactions
        verify(mockInputBoundary, times(1)).createPost(any(PostRequestModel.class));
    }
}