package entitytesting;

import usecases.requests.RegistrationData;
import entities.user.Client;
import entities.Question;
import entities.factories.ClientFactory;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
/**
 * This class contains unit tests for the Client class.
 */
class ClientTest {

    int expectedUserId = 1000000;
    String expectedName = "Xingfu Wu";
    String expectedEmail = "xingfu.wu@mail.utoronto.ca";
    String expectedPassword = "password";
    String expectedPassword2 = "password";
    String expectedStateAbb = "CA";
    String expectedPostalCode = "12345";
    String expectedEthnicity = "Asian";
    int expectedAge = 30;
    String expectedGender = "Male";
    String expectedMaritalStatus = "Single";
    int expectedNumberOfHousehold = 1;
    float expectedAnnualIncome = 60000.0f;

    /**
     * Tests the constructor and getter methods of the Client class.
     */
    @Test
    void testConstructorAndGetter() {
        // constructor
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        client.setUserId(expectedUserId);

        // assertions
        assertEquals(expectedUserId, client.getUserId(), "UserId is wrong.");
        assertEquals(expectedName, client.getUserName(), "User name is incorrect.");
        assertEquals(expectedEmail, client.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, client.getPassword(), "Password is wrong.");
        assertEquals(expectedStateAbb, client.getStateAbb(), "State abbreviation is incorrect.");
        assertEquals(expectedPostalCode, client.getPostalCode(), "Postal code is wrong.");
        assertEquals(expectedEthnicity, client.getEthnicity(), "Ethnicity is incorrect.");
        assertEquals(expectedAge, client.getAge(), "Age is incorrect.");
        assertEquals(expectedGender, client.getGender(), "Gender is incorrect.");
        assertEquals(expectedMaritalStatus, client.getMaritalStatus(), "Marital status is incorrect.");
        assertEquals(expectedNumberOfHousehold, client.getNumberOfHousehold(), "Number of household is incorrect.");
        assertEquals(expectedAnnualIncome, client.getAnnualIncome(), "Annual income is incorrect.");
        assertTrue(client.getQuestionsList().isEmpty(), "Questions list should be empty initially.");
        assertEquals("Client", client.getUserType(), "isClient should be true for Client.");
    }

    /**
     * Tests the setter methods of the Client class.
     */
    @Test
    void testSetters() {
        // no-arg constructor
        Client client = new Client();

        // use setters to set values
        client.setUserId(expectedUserId);
        client.setUserName(expectedName);
        client.setEmail(expectedEmail);
        client.setPassword(expectedPassword);
        client.setStateAbb(expectedStateAbb);
        client.setPostalCode(expectedPostalCode);
        client.setEthnicity(expectedEthnicity);
        client.setAge(expectedAge);
        client.setGender(expectedGender);
        client.setMaritalStatus(expectedMaritalStatus);
        client.setNumberOfHousehold(expectedNumberOfHousehold);
        client.setAnnualIncome(expectedAnnualIncome);

        // assertions
        assertEquals(expectedUserId, client.getUserId(), "UserId is wrong.");
        assertEquals(expectedName, client.getUserName(), "User name is incorrect.");
        assertEquals(expectedEmail, client.getEmail(), "Email is wrong.");
        assertEquals(expectedPassword, client.getPassword(), "Password is wrong.");
        assertEquals(expectedStateAbb, client.getStateAbb(), "State abbreviation is incorrect.");
        assertEquals(expectedPostalCode, client.getPostalCode(), "Postal code is wrong.");
        assertEquals(expectedEthnicity, client.getEthnicity(), "Ethnicity is incorrect.");
        assertEquals(expectedAge, client.getAge(), "Age is incorrect.");
        assertEquals(expectedGender, client.getGender(), "Gender is incorrect.");
        assertEquals(expectedMaritalStatus, client.getMaritalStatus(), "Marital status is incorrect.");
        assertEquals(expectedNumberOfHousehold, client.getNumberOfHousehold(), "Number of household is incorrect.");
        assertEquals(expectedAnnualIncome, client.getAnnualIncome(), "Annual income is incorrect.");
    }

    /**
     * Tests whether a question can be closed successfully for a client.
     */
    @Test
    void testClientIsQuestionCloseableSucceed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        Question question = new Question();
        question.setTaken(true);

        assertTrue(client.isQuestionCloseable(question));
    }

    /**
     * Tests whether a closed question is not closeable for a client.
     */
    @Test
    void testClientIsQuestionCloseableFail() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        Question question = new Question();
        question.setClose(true);

        assertFalse(client.isQuestionCloseable(question));
    }

    /**
     * Tests whether a question is selectable for a client.
     */
    @Test
    void testClientIsQuestionSelectable() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        Question question = new Question();

        assertTrue(client.isQuestionSelectable(question));

    }

    /**
     * Tests whether a question can be replied to successfully for a client.
     */
    @Test
    void testClientIsQuestionReplyableSucceed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        Question question = new Question();
        question.setClose(false);

        assertTrue(client.isQuestionReplyable(question));

    }

    /**
     * Tests whether a closed question is not replyable for a client.
     */
    @Test
    void testClientIsQuestionReplyableFail() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        Question question = new Question();
        question.setClose(true);

        assertFalse(client.isQuestionReplyable(question));

    }

    /**
     * Tests the addition of a question to the client's question list.
     */
    @Test
    void testAddQuestion() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        Question question = new Question(2000000, "fraud", "Fraud question", LocalDate.now(), 3000000, LocalDate.now());
        client.addQuestion(question);

        assertEquals(1, client.getQuestionsList().size(), "The size of the questions list is not correct");
        assertTrue(client.getQuestionsList().contains(question), "The question is not added in the list.");
    }

    /**
     * Tests the hash code generation of the Client class.
     */
    @Test
    void testHashCodeSucceed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        client.setUserId(expectedUserId);

        assertEquals(client.hashCode(), Objects.hashCode(1000000), "The hashcode is wrong");
    }

    /**
     * Tests whether the generated hash code differs when expected and actual user IDs do not match.
     */
    @Test
    void testHashCodeFail() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        assertNotEquals(client.hashCode(), Objects.hashCode(100000), "The hashcode is wrong");
    }

    /**
     * Tests inequality between two Client objects with non-matching data.
     */
    @Test
    void testEqualsFailByNotEqual() {
        RegistrationData registrationData1 = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        RegistrationData registrationData2 = new RegistrationData("joseph", expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client1 = clientFactory.createUser(registrationData1);
        client1.setUserId(1);
        Client client2 = clientFactory.createUser(registrationData2);
        client2.setUserId(expectedUserId);

        assertNotEquals(client1, client2, "The equal method is wrong");
    }

    /**
     * Test the toString method of the Client class when it succeeds.
     */
    @Test
    void testToStringSucceed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        String expectedToString = String.format("[Client]: %s", expectedName);
        assertEquals(expectedToString, client.toString(), "The toString method is wrong");
    }

    /**
     * Test the toString method of the Client class when it fails.
     */
    @Test
    void testToStringFail() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);

        String expectedToString = String.format("[Attorney]: %s", expectedName);
        assertNotSame(expectedToString, client.toString(), "The toString method is wrong");
    }

    /**
     * Tests whether a closed question is rateable for a client.
     */
    @Test
    void testIsQuestionRateableSucceed() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        Question question = new Question();
        question.setClose(true);
        assertTrue(client.isQuestionRateable(question), "IsQuestionRateable is wrong.");
    }

    /**
     * Tests whether a non-closed question is not rateable for a client.
     */
    @Test
    void testIsQuestionRateableFail() {
        RegistrationData registrationData = new RegistrationData(expectedName, expectedEmail, expectedPassword, expectedPassword2, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);
        ClientFactory clientFactory = new ClientFactory();
        Client client = clientFactory.createUser(registrationData);
        Question question = new Question();
        question.setClose(false);
        assertFalse(client.isQuestionRateable(question), "IsQuestionRateable is wrong.");
    }
}
