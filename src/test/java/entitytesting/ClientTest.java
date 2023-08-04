package entitytesting;

import entity.Client;
import entity.Question;
import org.junit.jupiter.api.Test;
import entity.Question;
import entity.Client;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ClientTest {

    @Test
    void testConstructorAndGetter() {
        // expected values
        int expectedUserId = 1000000;
        String expectedName = "Xingfu Wu";
        String expectedEmail = "xingfu.wu@mail.utoronto.ca";
        String expectedPassword = "password";
        String expectedStateAbb = "CA";
        String expectedPostalCode = "12345";
        String expectedEthnicity = "Asian";
        int expectedAge = 30;
        String expectedGender = "Male";
        String expectedMaritalStatus = "Single";
        int expectedNumberOfHousehold = 1;
        float expectedAnnualIncome = 60000.0f;

        // constructor
        Client client = new Client(expectedUserId, expectedName, expectedEmail, expectedPassword, expectedStateAbb,
                expectedPostalCode, expectedEthnicity, expectedAge, expectedGender, expectedMaritalStatus,
                expectedNumberOfHousehold, expectedAnnualIncome);

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
        assertTrue(client.isClient(), "isClient should be true for Client.");
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
        String expectedEthnicity = "Asian";
        int expectedAge = 30;
        String expectedGender = "Male";
        String expectedMaritalStatus = "Single";
        int expectedNumberOfHousehold = 1;
        float expectedAnnualIncome = 60000.0f;

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

    @Test
    void testClientIsQuestionCloseableSucceed() {
        Client client = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "abcdefghi",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        Question question = new Question();

        assertEquals(true, client.isQuestionCloseable(question));

    }

    @Test
    void testClientIsQuestionCloseableFail() {
        Client client = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "abcdefghi",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        Question question = new Question();
        question.setClose(true);

        assertEquals(false, client.isQuestionCloseable(question));

    }

    @Test
    void testClientIsQuestionSelectable() {
        Client client = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "abcdefghi",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        Question question = new Question();

        assertEquals(true, client.isQuestionSelectable(question));

    }

    @Test
    void testClientIsQuestionReplyableSucceed() {
        Client client = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "abcdefghi",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        Question question = new Question();
        question.setClose(false);

        assertEquals(true, client.isQuestionReplyable(question));

    }

    @Test
    void testAddQuestion() {
        // Create a Client object
        Client client = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        // Create a Question object
        Question question = new Question(2000000, "fraud", "Fraud question", LocalDate.now(), 3000000, LocalDate.now());

        // Add the question to the client
        client.addQuestion(question);

        // Assertions
        assertEquals(1, client.getQuestionsList().size(), "The size of the questions list should be 1 after adding a question.");
        assertTrue(client.getQuestionsList().contains(question), "The question should be in the questions list after adding.");
    }

    @Test
    void testEqualsAndHashCode() {
        // Create two equal Client objects
        Client client1 = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 60000.0f);
        Client client2 = new Client(1000000, "Xingfu Wu", "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        // Assertions
        assertEquals(client1, client2, "Two clients with the same user id should be equal.");
        assertEquals(client1.hashCode(), client2.hashCode(), "Two clients with the same user id should have the same hash code.");

        // Change the user id of the second client
        client2.setUserId(2000000);

        // Assertions
        assertNotEquals(client1, client2, "Two clients with different user ids should not be equal.");
        assertNotEquals(client1.hashCode(), client2.hashCode(), "Two clients with different user ids should have different hash codes.");
    }

    @Test
    void testToString() {
        // Expected value
        String expectedName = "Xingfu Wu";

        // Create a Client object
        Client client = new Client(1000000, expectedName, "xingfu.wu@mail.utoronto.ca", "password", "CA", "12345",
                "Asian", 30, "Male", "Single", 1, 60000.0f);

        // Create the expected toString return
        String expectedToString = String.format("[Client]: %s", expectedName);

        // Assertion
        assertEquals(expectedToString, client.toString(), "The toString method does not work as expected.");
    }
}
