package gatewaytesting;

import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.DatabaseConnection;
import driver.database.QuestionRepo;
import entity.Question;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entity.Attorney;
import entity.Client;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {

    //Client
    final static int CLIENT_ID = 100;
    final static String CLIENT_USERNAME = "bob";

    //Attorney
    final static int ATTORNEY_ID = 50;

    //Question
    final static int QUESTION_ID = 15;
    final static int QUESTION_ID1 = 25;
    final static int QUESTION_ID2 = 35;

    @BeforeAll
    public static void setUp() {
        //Client
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        Client c = new Client(CLIENT_ID, CLIENT_USERNAME, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientRepository repo = new ClientRepository();

        repo.deleteAll();
        repo.save(c);

        //Attorney
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        Attorney a = new Attorney(ATTORNEY_ID, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);

        AttorneyRepository aRepo = new AttorneyRepository();

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

    @Test
    public void testExistsById(){
        ClientRepository repo = new ClientRepository();
        //test id exists
        assertTrue(repo.existsById(CLIENT_ID), "The id does not exist!");
        //test id does not exist
        assertFalse(repo.existsById(200), "The id exists!");
    }

    @Test
    public void testExistsByName() {
        ClientRepository repo = new ClientRepository();
        //test username exists
        assertTrue(repo.existsByName(CLIENT_USERNAME), "The username does not exist!");
        //test username does not exist
        assertFalse(repo.existsByName("John"), "The username exists!");
    }

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

    @AfterAll
    public static void tearDown() {
        ClientRepository repo = new ClientRepository();
        AttorneyRepository aRepo = new AttorneyRepository();
        QuestionRepo qRepo = new QuestionRepo();
        repo.deleteAll();
        aRepo.deleteAll();
        qRepo.deleteAll();
    }

    @Test
    public void testGetUser() {
        int clientId = 100;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        Client c = new Client(clientId, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAll();
        repo.save(c);

        //test getting the correct client
        assertEquals(c, repo.get(100), "That is not the correct client!");
    }

    @Test
    public void testAddUser() {
        int clientId = 100;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        Client c = new Client(clientId, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAll();

        //test adding a client into the database
        repo.save(c);
        assertTrue(repo.existsById(100), "The client is not added!");
    }

    @Test
    public void testDeleteUser() {
        int clientId = 100;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        Client c = new Client(clientId, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
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

    @Test
    public void testDeleteAllUser() {
        int clientId = 100;
        int clientId2 = 200;
        String clientUsername = "bob";
        String clientEmail = "bob.bob@gmail.com";
        String clientPassword = "bob123";
        String clientState = "ON";
        String clientPostalCode = "M1MA6A";
        String clientEthnicity = "asian";
        int clientAge = 20;
        String clientGender = "Male";
        String clientMaritalStatus = "Single";
        int clientNumHouseHold = 1;
        float clientAnnualIncome = 100;

        //constructors
        Client c = new Client(clientId, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
        Client c1 = new Client(clientId2, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);
        Attorney a = new Attorney(150, "obo", "obo.obo@hotmail.com", "obo321",
                "NO", "A6AM1M");
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

        //recover
        repo.save(c);
        repo.save(c1);
    }

}