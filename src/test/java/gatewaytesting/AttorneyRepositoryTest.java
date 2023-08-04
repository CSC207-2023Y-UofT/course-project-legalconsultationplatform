package gatewaytesting;

import driver.database.*;
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

public class AttorneyRepositoryTest {

    //Attorney
    final static int ATTORNEY_ID = 50;
    final static String ATTORNEY_USERNAME = "yao";

    //Client
    final static int CLIENT_ID = 100;

    //Question
    final static int QUESTION_ID = 15;
    final static int QUESTION_ID1 = 25;
    final static int QUESTION_ID2 = 35;
    final static int ASKED_BY_CLIENT = 10;
    final static int ASKED_BY_CLIENT1 = 20;
    final static int ASKED_BY_CLIENT2 = 30;

    @BeforeAll
    public static void setUp() {
        //Attorney
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        Attorney a = new Attorney(ATTORNEY_ID, ATTORNEY_USERNAME, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);

        AttorneyRepository repo = new AttorneyRepository();

        repo.deleteAllUser();
        repo.addUser(a);

        //Client
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

        Client c = new Client(CLIENT_ID, clientUsername, clientEmail, clientPassword, clientState, clientPostalCode,
                clientEthnicity, clientAge, clientGender, clientMaritalStatus, clientNumHouseHold, clientAnnualIncome);

        ClientRepository cRepo = new ClientRepository();

        cRepo.deleteAllUser();
        cRepo.addUser(c);

        //Question
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        LocalDate legalDeadLine = LocalDate.now();

        Question q = new Question(QUESTION_ID, type, title, createAt, ASKED_BY_CLIENT, legalDeadLine);
        Question q1 = new Question(QUESTION_ID1, type, title, createAt, ASKED_BY_CLIENT1, legalDeadLine);
        Question q2 = new Question(QUESTION_ID2, type, title, createAt, ASKED_BY_CLIENT2, legalDeadLine);

        QuestionRepo qRepo = new QuestionRepo();

        qRepo.deleteAllQuestion();
        q.setTakenByAttorney(ATTORNEY_ID);
        q2.setTakenByAttorney(ATTORNEY_ID);
        qRepo.saveQuestion(q);
        qRepo.saveQuestion(q1);
        qRepo.saveQuestion(q2);
    }

    @Test
    public void testExistsById(){
        AttorneyRepository repo = new AttorneyRepository();
        //test id exists
        assertTrue(repo.existsById(ATTORNEY_ID), "The id does not exist!");
        //test id does not exist
        assertFalse(repo.existsById(75), "The id exists!");
    }

    @Test
    public void testExistsByName() {
        AttorneyRepository repo = new AttorneyRepository();
        //test username exists
        assertTrue(repo.existsByName(ATTORNEY_USERNAME), "The username does not exist!");
        //test username does not exist
        assertFalse(repo.existsByName("John"), "The username exists!");
    }

    @Test
    public void testGetAllQuestionById() {
        AttorneyRepository repo = new AttorneyRepository();
        QuestionRepo qRepo = new QuestionRepo();
        //set up
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(qRepo.getQuestion(QUESTION_ID));
        expectedList.add(qRepo.getQuestion(QUESTION_ID2));
        //test get all Post by Question
        assert expectedList.equals(repo.getAllQuestionById(ATTORNEY_ID));
    }

    @Test
    public void testUpdateQuestionList() {
        AttorneyRepository repo = new AttorneyRepository();
        QuestionRepo qRepo = new QuestionRepo();
        //test updating list
        repo.updateQuestionList(ATTORNEY_ID, qRepo.getQuestion(QUESTION_ID));
        ArrayList<Question> expectedList1 = new ArrayList<>();
        expectedList1.add(qRepo.getQuestion(QUESTION_ID));
        assert expectedList1.equals(repo.getUser(ATTORNEY_ID).getQuestionsList());
    }

    @AfterAll
    public static void tearDown() {
        AttorneyRepository repo = new AttorneyRepository();
        ClientRepository cRepo = new ClientRepository();
        QuestionRepo qRepo = new QuestionRepo();
        repo.deleteAllUser();
        cRepo.deleteAllUser();
        qRepo.deleteAllQuestion();
    }

    @Test
    public void testGetUser() {
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        //constructors
        Attorney a = new Attorney(ATTORNEY_ID, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);
        AttorneyRepository repo = new AttorneyRepository();

        //set up
        repo.deleteAllUser();
        repo.addUser(a);

        //test get the correct attorney
        assertEquals(a, repo.getUser(ATTORNEY_ID), "That is not the correct client!");
    }

    @Test
    public void testAddUser() {
        int attorneyId = 50;
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        //constructors
        Attorney a = new Attorney(attorneyId, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);
        AttorneyRepository repo = new AttorneyRepository();

        //set up
        repo.deleteAllUser();

        //test adding a attorney into the database
        repo.addUser(a);
        assertTrue(repo.existsById(attorneyId), "The attorney is not added!");
    }

    @Test
    public void testDeleteUser() {
        int attorneyId = 50;
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        //constructors
        Attorney a = new Attorney(attorneyId, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);
        AttorneyRepository repo = new AttorneyRepository();

        //set up
        repo.deleteAllUser();
        repo.addUser(a);

        //test delete an existing attorney from the database
        assertTrue(repo.existsById(attorneyId), "The attorney was not added!");
        repo.deleteUser(attorneyId);
        assertFalse(repo.existsById(attorneyId), "The attorney is still in the database!");

        //recover
        repo.addUser(a);
    }

    @Test
    public void testDeleteAllUser() {
        int attorneyId = 50;
        int attorneyId2 = 55;
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        //constructors
        Attorney a = new Attorney(attorneyId, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);
        Attorney a1 = new Attorney(attorneyId2, attorneyUsername, attorneyEmail, attorneyPassword,
                attorneyState, attorneyPostalCode);
        Client c = new Client(100, "bob", "bob.bob@gmail.com", "bob123", "ON",
                "M1MA6A", "asian", 20, "Male", "Single", 1,
                100);
        AttorneyRepository repo = new AttorneyRepository();
        ClientRepository clientRepo = new ClientRepository();

        //set up
        repo.deleteAllUser();
        clientRepo.deleteAllUser();
        repo.addUser(a);
        repo.addUser(a1);
        clientRepo.addUser(c);

        //test remove two users
        repo.deleteAllUser();
        assertFalse(repo.existsById(attorneyId), "Attorney a has not been removed!");
        assertFalse(repo.existsById(attorneyId2), "Attorney a1 has not been removed!");

        //set up
        repo.addUser(a);
        repo.addUser(a1);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all client
        repo.deleteAllUser();
        Long count = em.createQuery("SELECT COUNT(a) FROM Attorney a", Long.class).getSingleResult();
        assertEquals(0, count, "The database still has saved client objects!");
        //test client a still in database
        assertTrue(clientRepo.existsById(100), "client c is no longer in the database");
        //test remove empty
        repo.deleteAllUser();
        assertEquals(0, count, "The database still has saved client objects!");

        //recover
        repo.addUser(a);
        repo.addUser(a1);
    }

}
