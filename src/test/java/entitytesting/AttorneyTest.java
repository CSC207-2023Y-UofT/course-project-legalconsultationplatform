package entitytesting;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import questionentities.Question;
import userentities.Attorney;

import java.time.LocalDate;

class AttorneyTest {

    @Test
    void testConstructorAndGetter() {
        // expected values
        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";

        // constructor
        Attorney attorney = new Attorney(expectedUserId, expectedName, expectedEmail, expectedPassword, expectedStateAbb, expectedPostalCode);

        // assertions
        assertEquals(expectedUserId, attorney.getUserId(), "UserId is wrong.");
        assertEquals(expectedName, attorney.getName(), "Name is incorrect.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is wrong.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "State abbreviation is incorrect.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "Postal code is wrong.");
        assertTrue(attorney.getQuestionsList().isEmpty(), "Questions list should be empty initially.");
        assertFalse(attorney.isClient(), "isClient should be false for Attorney.");
    }

    @Test
    void testSetters() {
        // expected values
        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";

        // no-arg constructor
        Attorney attorney = new Attorney();

        // use setters to set values
        attorney.setUserId(expectedUserId);
        attorney.setName(expectedName);
        attorney.setEmail(expectedEmail);
        attorney.setPassword(expectedPassword);
        attorney.setStateAbb(expectedStateAbb);
        attorney.setPostalCode(expectedPostalCode);

        // assertions
        assertEquals(expectedUserId, attorney.getUserId(), "UserId is wrong.");
        assertEquals(expectedName, attorney.getName(), "Name is wrong.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is wrong.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "State abbreviation is wrong.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "Postal code is wrong.");
    }

    @Test
    void testIsQuestionCloseableAndSelectable() {
        // create an attorney
        Attorney attorney = new Attorney(1000000, "John Doe", "johndoe@example.com", "password", "CA", "90001");

        // create a question
        Question question = new Question(2000000, "fraud", LocalDate.now(), 3000000, LocalDate.now());

        // check if the question is closeable and selectable
        assertFalse(attorney.isQuestionCloseable(question), "The question should not be closeable initially.");
        assertTrue(attorney.isQuestionSelectable(question), "The question should be selectable initially.");

        // set the question as taken by the attorney
        question.setTaken(true);
        question.setTakenByAttorney(attorney.getUserId());

        // check if the question is closeable and selectable
        assertTrue(attorney.isQuestionCloseable(question), "The question should be closeable when taken by the attorney.");
        assertTrue(attorney.isQuestionSelectable(question), "The question should be selectable when taken by the attorney.");

        // set the question as closed
        question.setClose(true);

        // check if the question is closeable and selectable
        assertFalse(attorney.isQuestionCloseable(question), "The question should not be closeable when it is already closed.");
        assertFalse(attorney.isQuestionSelectable(question), "The question should not be selectable when it is already closed.");
    }
}
