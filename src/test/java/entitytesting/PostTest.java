package entitytesting;

import org.junit.jupiter.api.Test;
import questionentities.Post;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostTest {

    @Test
    public void testPostEntityMethod(){
        int expectedQuestionId = 1000000;
        int expectedPostId = 2000000;
        LocalDate expectedCreateAt = LocalDate.now();
        String testText = "Test";

        Post post = new Post(expectedQuestionId, expectedPostId, expectedCreateAt, testText);
        assertEquals(expectedQuestionId, post.getQuestionId(), "QuestionId is wrong.");
        assertEquals(expectedPostId, post.getPostId(), "PostId is wrong.");
        assertEquals(expectedCreateAt, post.getCreateAt(), "CreateAt is wrong.");
        assertEquals(testText, post.getPostText(), "PostText is wrong.");
    }

}

