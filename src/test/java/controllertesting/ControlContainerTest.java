package controllertesting;

import adapters.controllers.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

/**
 * This class contains unit tests for the ControlContainer class.
 */
public class ControlContainerTest {

    /**
     * Tests the getClientRegisterControl method of ControlContainer.
     */
    @Test
    public void testGetClientRegisterControl() {
        ClientRegisterControl mockClientRegisterControl = mock(ClientRegisterControl.class);
        ControlContainer controlContainer = new ControlContainer(mockClientRegisterControl, null, null, null, null, null, null, null, null, null);
        assertEquals(mockClientRegisterControl, controlContainer.getClientRegisterControl());
    }

    /**
     * Tests the getCloseQuestionControl method of ControlContainer.
     */
    @Test
    public void testGetCloseQuestionControl() {
        CloseQuestionControl mockCloseQuestionControl = mock(CloseQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, mockCloseQuestionControl, null, null, null, null, null, null, null, null);
        assertEquals(mockCloseQuestionControl, controlContainer.getCloseQuestionControl());
    }

    /**
     * Tests the getPostControl method of ControlContainer.
     */
    @Test
    public void testGetPostControl() {
        PostControl mockPostControl = mock(PostControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, mockPostControl, null, null, null, null, null, null, null);
        assertEquals(mockPostControl, controlContainer.getPostControl());
    }

    /**
     * Tests the getQuestionControl method of ControlContainer.
     */
    @Test
    public void testGetQuestionControl() {
        QuestionControl mockQuestionControl = mock(QuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, mockQuestionControl, null, null, null, null, null, null);
        assertEquals(mockQuestionControl, controlContainer.getQuestionControl());
    }

    /**
     * Tests the getRateControl method of ControlContainer.
     */
    @Test
    public void testGetRateControl() {
        RateControl mockRateControl = mock(RateControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, mockRateControl, null, null, null, null, null);
        assertEquals(mockRateControl, controlContainer.getRateControl());
    }

    /**
     * Tests the getSelectQuestionControl method of ControlContainer.
     */
    @Test
    public void testGetSelectQuestionControl() {
        SelectQuestionControl mockSelectQuestionControl = mock(SelectQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, mockSelectQuestionControl, null, null, null, null);
        assertEquals(mockSelectQuestionControl, controlContainer.getSelectQuestionControl());
    }

    /**
     * Tests the getUserLoginControl method of ControlContainer.
     */
    @Test
    public void testGetUserLoginControl() {
        UserLoginControl mockUserLoginControl = mock(UserLoginControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, mockUserLoginControl, null, null, null);
        assertEquals(mockUserLoginControl, controlContainer.getUserLoginControl());
    }

    /**
     * Tests the getViewQuestionControl method of ControlContainer.
     */
    @Test
    public void testGetViewQuestionControl() {
        ViewQuestionControl mockViewQuestionControl = mock(ViewQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, null, mockViewQuestionControl, null, null);
        assertEquals(mockViewQuestionControl, controlContainer.getViewQuestionControl());
    }

    @Test
    public void testGetBrowseQuestionControl() {
        ViewQuestionControl mockBrowseQuestionControl = mock(ViewQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, null, null, mockBrowseQuestionControl, null);
        assertEquals(mockBrowseQuestionControl, controlContainer.getBrowseQuestionControl());
    }

    @Test
    public void testGetRecommendationControl() {
        ViewQuestionControl mockRecommendationControl = mock(ViewQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, null, null, null, mockRecommendationControl);
        assertEquals(mockRecommendationControl, controlContainer.getRecommendationControl());
    }
}
