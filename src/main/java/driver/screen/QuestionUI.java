package driver.screen;

import adapter.controller.ControlContainer;
import entity.Post;

import java.util.List;

public abstract class QuestionUI extends UserUI{
    protected List<Post> postList;
    public QuestionUI(String userName, int userId, ControlContainer controlContainer) {
        super(userName, userId, controlContainer);
    }
}
