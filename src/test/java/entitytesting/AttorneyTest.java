package entitytesting;
import org.junit.jupiter.api.Test;
import questionentities.Question;
import userentities.Attorney;

import java.time.LocalDate;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;
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
        assertEquals(expectedUserId, attorney.getUserId(), "UserId is not set correctly in the constructor.");
        assertEquals(expectedName, attorney.getName(), "Name is not set correctly in the constructor.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is not set correctly in the constructor.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is not set correctly in the constructor.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "State abbreviation is not set correctly in the constructor.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "Postal code is not set correctly in the constructor.");
        assertTrue(attorney.getQuestionsList().isEmpty(), "Questions list should be empty initially.");
        assertFalse(attorney.isClient(), "isClient should return false for Attorney.");
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
        assertEquals(expectedUserId, attorney.getUserId(), "UserId is incorrect.");
        assertEquals(expectedName, attorney.getName(), "Name is incorrect.");
        assertEquals(expectedEmail, attorney.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, attorney.getPassword(), "Password is not set correctly.");
        assertEquals(expectedStateAbb, attorney.getStateAbb(), "State abbreviation is not set correctly.");
        assertEquals(expectedPostalCode, attorney.getPostalCode(), "Postal code is not set correctly.");
    }

    @Test
    void testIsQuestionCloseableAndSelectable() {
        // create an attorney
        Attorney attorney = new Attorney(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345");

        // create a question
        Question question = new Question(2000000, "fraud", "A question about fraud", LocalDate.now(), 3000000, LocalDate.now());


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

    @Test
    void testEquals() {
        // create two identical attorneys
        Attorney attorney1 = new Attorney(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "90001");
        Attorney attorney2 = new Attorney(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "90001");

        // check if they are equal
        assertEquals(attorney1, attorney2, "Two attorneys with the same properties should be equal.");

        // change one property of the second attorney
        attorney2.setUserId(2000000);

        // check if they are not equal
        assertNotEquals(attorney1, attorney2, "Two attorneys with different properties should not be equal.");
    }

    @Test
    void testToString() {
        // expected values
        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";

        // create an attorney
        Attorney attorney = new Attorney(expectedUserId, expectedName, expectedEmail, expectedPassword, expectedStateAbb, expectedPostalCode);

        // construct the expected string representation
        String expectedToString = String.format("[Attorney]: %s", expectedName);

        // assert that the actual string representation matches the expected string representation
        assertEquals(expectedToString, attorney.toString(), "The output of toString is not as expected.");
    }

}
