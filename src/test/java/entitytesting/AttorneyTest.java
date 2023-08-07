package entitytesting;
import entity.Attorney;
import entity.Client;
import entity.Question;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
class AttorneyTest {

    @Test
    void testConstructorAndGetter() {
        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";

        Attorney attorney = new Attorney(expectedUserId, expectedName, expectedEmail, expectedPassword, expectedStateAbb,
                expectedPostalCode);

        assertEquals(expectedUserId, attorney.getUserId(), "UserId is not set correctly in the constructor.");
        assertEquals(expectedName, attorney.getUserName(), "Name is set correctly in the constructor.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is not set correctly in the constructor.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is not set correctly in the constructor.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "State abbreviation is not set correctly in the constructor.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "Postal code is not set correctly in the constructor.");
    }

    @Test
    void testSetters() {

        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";

        Attorney attorney = new Attorney();

        attorney.setUserId(expectedUserId);
        attorney.setName(expectedName);
        attorney.setEmail(expectedEmail);
        attorney.setPassword(expectedPassword);
        attorney.setStateAbb(expectedStateAbb);
        attorney.setPostalCode(expectedPostalCode);

        assertEquals(expectedUserId, attorney.getUserId(), "UserId is incorrect.");
        assertEquals(expectedName, attorney.getUserName(), "Name is incorrect.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is not set correctly.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "The state abbreviation should be updated.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "The postal code should be updated.");
    }
    @Test
    void testAddQuestion() {
        Attorney attorney = new Attorney(1, "Test Attorney", "attorney@example.com", "password", "CA", "12345");
        Question question1 = new Question();
        question1.setQuestionId(1);
        question1.setTitle("First Question");
        Question question2 = new Question();
        question2.setQuestionId(2);
        question2.setTitle("Second Question");
        attorney.addQuestion(question1);
        attorney.addQuestion(question2);
        List<Question> questions = attorney.getQuestionsList();
        assertEquals(2, questions.size(), "The size of the question list should be 2 after adding two questions.");
        assertNotEquals(-1, questions.indexOf(question1), "The first new question should be in the question list.");
        assertNotEquals(-1, questions.indexOf(question2), "The second new question should be in the question list.");
        attorney.addQuestion(question1);
        assertEquals(2, questions.size(), "After adding a duplicate question, the number of questions in the list should remain at 2.");
    }
    @Test
    void testCloseableIfNotTaken() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");

        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(false);

        boolean expected = false;
        boolean actual = attorney.isQuestionCloseable(question);
        assertEquals(expected, actual, "The question should not be closeable when it is not taken.");
    }
    @Test
    void testCloseableIfTakenByOthers() {
        Attorney attorney1 = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Attorney attorney2 = new Attorney(2, "Attorney2", "attorney2@example.com", "password2", "CA", "12345");

        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney2.getUserId());

        boolean expected = false;
        boolean actual = attorney1.isQuestionCloseable(question);
        assertEquals(expected, actual, "The question should not be closeable when it is taken by a different attorney.");
    }
    @Test
    void testCloseableIfTakenBySelf() {

        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney.getUserId());

        boolean expected = true;
        boolean actual = attorney.isQuestionCloseable(question);
        assertEquals(expected, actual, "The question should be closeable when it is taken by the current attorney.");
    }
    @Test
    void testIsClient() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "23456");
        boolean expected = false;
        boolean actual = attorney.isClient();
        assertEquals(expected, actual, "The isClient method of an Attorney should always return false.");
    }
    @Test
    void testSelectableIfClosed() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1",
                "CA", "12345");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setClose(true);
        boolean expected = false;
        boolean actual = attorney.isQuestionSelectable(question);
        assertEquals(expected, actual, "The question should not be selectable when it is closed.");
}
    @Test
    void testSelectableIfNotTaken() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(false);
        boolean expected = true;
        boolean actual = attorney.isQuestionSelectable(question);
        assertEquals(expected, actual, "The question should be selectable when it is not taken.");
    }
    @Test
    void testSelectableIfTakenByOthers() {
        Attorney attorney1 = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Attorney attorney2 = new Attorney(2, "Attorney2", "attorney2@example.com", "password2", "CA", "23456");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney2.getUserId());
        boolean expected = false;
        boolean actual = attorney1.isQuestionSelectable(question);
        assertEquals(expected, actual, "The question should not be selectable when it is taken by a different attorney.");
    }

    @Test
    void testSelectableIfTakenBySelf() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney.getUserId());
        boolean expected = true;
        boolean actual = attorney.isQuestionSelectable(question);
        assertEquals(expected, actual, "The question should be selectable when it is taken by the current attorney.");
    }

    @Test
    void testReplyableIfClosed() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "90210");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setClose(true);
        boolean expected = false;
        boolean actual = attorney.isQuestionReplyable(question);
        assertEquals(expected, actual, "The question should not be replyable when it is closed.");
    }
    @Test
    void testReplyableIfTakenByOthers() {
        Attorney attorney1 = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Attorney attorney2 = new Attorney(2, "Attorney2", "attorney2@example.com", "password2", "CA", "23456");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney2.getUserId());
        boolean expected = false;
        boolean actual = attorney1.isQuestionReplyable(question);
        assertEquals(expected, actual, "The question should not be replyable when it is taken by a different attorney.");
    }

    @Test
    void testReplyableIfTakenBySelf() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(true);
        question.setTakenByAttorney(attorney.getUserId());
        boolean expected = true;
        boolean actual = attorney.isQuestionReplyable(question);
        assertEquals(expected, actual, "The question should be replyable when it is taken by the current attorney.");
    }

    @Test
    void testReplyableIfNotTaken() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "90210");
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");
        question.setTaken(false);
        boolean expected = true;
        boolean actual = attorney.isQuestionReplyable(question);
        assertEquals(expected, actual, "The question should be replyable when it is not taken.");
        assertTrue(question.isTaken(), "The question should be marked as taken after isQuestionReplyable is called.");
        assertEquals(attorney.getUserId(), question.getTakenByAttorney(), "The attorney who took the question should be set to the current attorney after isQuestionReplyable is called.");
    }

    @Test
    void testEqualsSucced(){
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        assertEquals(true,attorney.equals(attorney),"The equal method is wrong");
    }
    @Test
    void testEqualFailByNotClient(){
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Question question = new Question();

        assertEquals(false,attorney.equals(question),"The equal method is wrong");
    }@Test
    void testEqualsFailByNotEqual(){
        Attorney attorney1 = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        Attorney attorney2 = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");
        assertEquals(true, attorney1.equals(attorney2),"The equal method is wrong");
    }


    @Test
    void testIsQuestionRateable() {
        Attorney attorney = new Attorney(1, "Attorney1", "attorney1@example.com", "password1", "CA", "12345");

        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");

        boolean expected = false;
        boolean actual = attorney.isQuestionRateable(question);
        assertEquals(expected, actual, "The isQuestionRateable method should always return false for an Attorney.");
    }

}
