package entitytesting;
import org.junit.jupiter.api.Test;
import questionentities.Post;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AttorneyTest {

    @Test
    public void testAttorneyEntityMethod(){
        int expectedUserId = 1000000;
        String expectedPassword = "a";
        String expectedStateAbb = "Test";

        Post post = new Post(expectedQuestionId, expectedPostId, expectedCreateAt, testText);
        assertEquals(expectedQuestionId, post.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedPostId, post.getPostId(), "PostId is wrong.");
        assertEquals(expectedCreateAt, post.getCreateAt(), "CreateAt is wrong.");
        assertEquals(testText, post.getPostText(), "PostText is wrong.");
    }

}
