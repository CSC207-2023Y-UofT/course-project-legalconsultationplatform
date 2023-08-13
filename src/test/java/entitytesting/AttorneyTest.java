package entitytesting;
import businessrule.requestmodel.RegistrationData;
import entity.Attorney;
import entity.Question;
import entity.factory.AttorneyFactory;
import org.junit.jupiter.api.Test;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class AttorneyTest {
    int expectedUserId = 1000000;
    String expectedName = "Xingfu Wu";
    String expectedEmail = "xingfu.wu@mail.utoronto.ca";
    String expectedPassword = "password";
    String expectedPassword2 = "password";
    String expectedStateAbb = "CA";
    String expectedPostalCode = "12345";

    @Test
    void testConstructorAndGetter() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
        attorney.setUserId(expectedUserId);

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
        attorney.setUserName(expectedName);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData1 = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney1  = attorneyFactory.createUser(registrationData1);
        RegistrationData registrationData2 = new RegistrationData("joseph", expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        Attorney attorney2  = attorneyFactory.createUser(registrationData2);

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
    void testUserType() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
        String expected = "Attorney";
        String actual = attorney.getUserType();
        assertEquals(expected, actual);
    }
    @Test
    void testSelectableIfClosed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData1 = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney1  = attorneyFactory.createUser(registrationData1);
        attorney1.setUserId(10000);
        RegistrationData registrationData2 = new RegistrationData("joseph", expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        Attorney attorney2  = attorneyFactory.createUser(registrationData2);
        attorney2.setUserId(20000);

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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData1 = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney1  = attorneyFactory.createUser(registrationData1);
        attorney1.setUserId(10000);
        RegistrationData registrationData2 = new RegistrationData("joseph", expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        Attorney attorney2  = attorneyFactory.createUser(registrationData2);
        attorney2.setUserId(20000);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
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
    void testEqualsSucceed(){
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
        assertEquals(true,attorney.equals(attorney),"The equal method is wrong");
    }
    @Test
    void testEqualFailByNotClient(){
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
        Question question = new Question();

        assertEquals(false,attorney.equals(question),"The equal method is wrong");
    }@Test
    void testEqualsFailByNotEqual(){
        RegistrationData registrationData1 = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney1  = attorneyFactory.createUser(registrationData1);
        RegistrationData registrationData2 = new RegistrationData("joseph", expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        Attorney attorney2  = attorneyFactory.createUser(registrationData2);
        assertEquals(true, attorney1.equals(attorney2),"The equal method is wrong");
    }


    @Test
    void testIsQuestionRateable() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney attorney  = attorneyFactory.createUser(registrationData);
        Question question = new Question();
        question.setQuestionId(1);
        question.setTitle("Test question");

        boolean expected = false;
        boolean actual = attorney.isQuestionRateable(question);
        assertEquals(expected, actual, "The isQuestionRateable method should always return false for an Attorney.");
    }

}
