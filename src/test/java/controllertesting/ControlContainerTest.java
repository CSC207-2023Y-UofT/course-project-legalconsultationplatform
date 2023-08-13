package controllertesting;

import adapter.controller.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ControlContainerTest {

    @Test
    public void testGetClientRegisterControl() {
        ClientRegisterControl mockClientRegisterControl = mock(ClientRegisterControl.class);
        ControlContainer controlContainer = new ControlContainer(mockClientRegisterControl, null, null, null, null, null, null, null);
        assertEquals(mockClientRegisterControl, controlContainer.getClientRegisterControl());
    }

    @Test
    public void testGetCloseQuestionControl() {
        CloseQuestionControl mockCloseQuestionControl = mock(CloseQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, mockCloseQuestionControl, null, null, null, null, null, null);
        assertEquals(mockCloseQuestionControl, controlContainer.getCloseQuestionControl());
    }

    @Test
    public void testGetPostControl() {
        PostControl mockPostControl = mock(PostControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, mockPostControl, null, null, null, null, null);
        assertEquals(mockPostControl, controlContainer.getPostControl());
    }

    @Test
    public void testGetQuestionControl() {
        QuestionControl mockQuestionControl = mock(QuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, mockQuestionControl, null, null, null, null);
        assertEquals(mockQuestionControl, controlContainer.getQuestionControl());
    }

    @Test
    public void testGetRateControl() {
        RateControl mockRateControl = mock(RateControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, mockRateControl, null, null, null);
        assertEquals(mockRateControl, controlContainer.getRateControl());
    }

    @Test
    public void testGetSelectQuestionControl() {
        SelectQuestionControl mockSelectQuestionControl = mock(SelectQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, mockSelectQuestionControl, null, null);
        assertEquals(mockSelectQuestionControl, controlContainer.getSelectQuestionControl());
    }

    @Test
    public void testGetUserLoginControl() {
        UserLoginControl mockUserLoginControl = mock(UserLoginControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, mockUserLoginControl, null);
        assertEquals(mockUserLoginControl, controlContainer.getUserLoginControl());
    }

    @Test
    public void testGetViewQuestionControl() {
        ViewQuestionControl mockViewQuestionControl = mock(ViewQuestionControl.class);
        ControlContainer controlContainer = new ControlContainer(null, null, null, null, null, null, null, mockViewQuestionControl);
        assertEquals(mockViewQuestionControl, controlContainer.getViewQuestionControl());
    }
}
