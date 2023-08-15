package gatewaytesting;

import usecases.requests.RegistrationData;
import infrastructure.database.AttorneyRepository;
import infrastructure.database.ClientRepository;
import infrastructure.database.DatabaseConnection;
import infrastructure.database.QuestionRepo;
import entities.Question;
import entities.factories.AttorneyFactory;
import entities.factories.ClientFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entities.user.Attorney;
import entities.user.Client;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

/**
 * This class contains unit tests for the ClientRepository class.
 */
public class ClientRepositoryTest {

    //Client
    final static int CLIENT_ID = 100;
    final static String CLIENT_USERNAME = "bob";

    //Question
    final static int QUESTION_ID = 15;
    final static int QUESTION_ID1 = 25;
    final static int QUESTION_ID2 = 35;

    /**
     * Set up the test environment by initializing the ClientRepository instance.
     */
    @BeforeAll
    public static void setUp() {
        //Client
        String clientName = "joseph";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        RegistrationData registrationData = new RegistrationData(clientName, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        Client c  = clientFactory.createUser(registrationData);
        ClientRepository repo = new ClientRepository();

        repo.deleteAll();
        repo.save(c);

        //Attorney
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123321";
        String attorneyPassword2 = "yao123321";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        AttorneyRepository aRepo = new AttorneyRepository();
        RegistrationData registrationData2 = new RegistrationData(attorneyUsername, attorneyEmail, attorneyPassword, attorneyPassword2, attorneyState, attorneyPostalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney a = attorneyFactory.createUser(registrationData2);

        aRepo.deleteAll();
        aRepo.save(a);

        //Question
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        LocalDate legalDeadLine = LocalDate.now();

        Question q = new Question(QUESTION_ID, type, title, createAt, CLIENT_ID, legalDeadLine);
        Question q1 = new Question(QUESTION_ID1, type, title, createAt, 123, legalDeadLine);
        Question q2 = new Question(QUESTION_ID2, type, title, createAt, CLIENT_ID, legalDeadLine);

        QuestionRepo qRepo = new QuestionRepo();

        qRepo.deleteAll();
        qRepo.save(q);
        qRepo.save(q1);
        qRepo.save(q2);
    }

    /**
     * Test whether a client ID exists in the repository.
     */
    @Test
    public void testExistsById(){
        ClientRepository repo = new ClientRepository();
        //test id exists
        assertTrue(repo.existsById(CLIENT_ID), "The id does not exist!");
        //test id does not exist
        assertFalse(repo.existsById(200), "The id exists!");
    }

    /**
     * Test whether a client with a given username exists in the repository.
     */
    @Test
    public void testExistsByName() {
        ClientRepository repo = new ClientRepository();
        //test username exists
        assertTrue(repo.existsByName(CLIENT_USERNAME), "The username does not exist!");
        //test username does not exist
        assertFalse(repo.existsByName("John"), "The username exists!");
    }

    /**
     * Test updating a client's question list.
     */
    @Test
    public void testUpdateQuestionList() {
        ClientRepository repo = new ClientRepository();
        QuestionRepo qRepo = new QuestionRepo();
        //test updating list
        repo.updateQuestionList(CLIENT_ID, qRepo.get(QUESTION_ID));
        ArrayList<Question> expectedList = new ArrayList<>();
        expectedList.add(qRepo.get(QUESTION_ID));
        assert expectedList.equals(repo.get(CLIENT_ID).getQuestionsList());
    }

    /**
     * Delete all data in AttorneyRepository, ClientRepository and QuestionRepo.
     */
    @AfterAll
    public static void tearDown() {
        ClientRepository repo = new ClientRepository();
        AttorneyRepository aRepo = new AttorneyRepository();
        QuestionRepo qRepo = new QuestionRepo();
        repo.deleteAll();
        aRepo.deleteAll();
        qRepo.deleteAll();
    }

    /**
     * Test getting a client from the repository.
     */
    @Test
    public void testGetUser() {
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        RegistrationData registrationData = new RegistrationData(clientUsername, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        Client c  = clientFactory.createUser(registrationData);
        c.setUserId(100);
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAll();
        repo.save(c);

        //test getting the correct client
        assertEquals(c, repo.get(100), "That is not the correct client!");
    }

    /**
     * Test adding a client to the repository.
     */
    @Test
    public void testAddUser() {
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        RegistrationData registrationData = new RegistrationData(clientUsername, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        Client c  = clientFactory.createUser(registrationData);
        c.setUserId(100);
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAll();

        //test adding a client into the database
        repo.save(c);
        assertTrue(repo.existsById(100), "The client is not added!");
    }

    /**
     * Test deleting a client in the repository.
     */
    @Test
    public void testDeleteUser() {
        int clientId = 100;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        RegistrationData registrationData = new RegistrationData(clientUsername, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        Client c  = clientFactory.createUser(registrationData);
        c.setUserId(clientId);
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAll();
        repo.save(c);

        //test delete an existing client from the database
        assertTrue(repo.existsById(clientId), "The client was not added!");
        repo.delete(clientId);
        assertFalse(repo.existsById(clientId), "The client is still in the database!");

        //recover
        repo.save(c);
    }

    /**
     * Test deleting all client in the repository.
     */
    @Test
    public void testDeleteAllUser() {
        int clientId = 100;
        int clientId2 = 200;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123321";
        String clientPassword2 = "bob123321";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        RegistrationData registrationData = new RegistrationData(clientUsername, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientFactory clientFactory = new ClientFactory();
        Client c  = clientFactory.createUser(registrationData);
        c.setUserId(clientId);
        RegistrationData registrationData2 = new RegistrationData(clientUsername, clientEmail, clientPassword, clientPassword2, clientState, clientPostalCode, clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
        Client c1 = clientFactory.createUser(registrationData2);
        c1.setUserId(clientId2);

        RegistrationData registrationData3 = new RegistrationData("obo", "obo.obo@hotmail.com", "obo123321", "obo123321",
                "NO", "A6AM1M");
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney a = attorneyFactory.createUser(registrationData3);
        a.setUserId(150);

        ClientRepository repo = new ClientRepository();
        AttorneyRepository attorneyRepo = new AttorneyRepository();
        //set up
        repo.deleteAll();
        attorneyRepo.deleteAll();
        repo.save(c);
        repo.save(c1);
        attorneyRepo.save(a);

        //test remove two users
        repo.deleteAll();
        assertFalse(repo.existsById(clientId), "Client c has not been removed!");
        assertFalse(repo.existsById(clientId2), "Client c1 has not been removed!");

        //set up
        repo.save(c);
        repo.save(c1);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all client
        repo.deleteAll();
        Long count = em.createQuery("SELECT COUNT(c) FROM Client c", Long.class).getSingleResult();
        assertEquals(0, count, "The database still has saved client objects!");
        //test attorney a still in database
        assertTrue(attorneyRepo.existsById(150), "Attorney a is no longer in the database");
        //test remove empty
        repo.deleteAll();
        assertEquals(0, count, "The database still has saved client objects!");

    }

}