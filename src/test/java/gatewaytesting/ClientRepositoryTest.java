package gatewaytesting;

import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import driver.database.DatabaseConnection;
import driver.database.QuestionRepo;
import entity.Question;
import org.junit.jupiter.api.Test;
import entity.Attorney;
import entity.Client;

import javax.persistence.EntityManager;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ClientRepositoryTest {

    @Test
    public void testExistsById(){
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
        Attorney a = new Attorney(150, "obo", "obo.obo@hotmail.com", "obo321",
                "NO", "A6AM1M");
        ClientRepository repo = new ClientRepository();
        AttorneyRepository attorneyRepo = new AttorneyRepository();

        //set up
        repo.deleteAllUser();
        attorneyRepo.deleteAllUser();
        repo.addUser(c);
        attorneyRepo.addUser(a);

        //test id exists
        assertTrue(repo.existsById(clientId), "The id does not exist!");
        //test id does not exist
        assertFalse(repo.existsById(200), "The id exists!");
        //test id belongs to Attorney
        assertFalse(repo.existsById(150), "The id exists!");
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
        repo.deleteAllUser();
        repo.addUser(c);

        //test getting the correct client
        assertEquals(c, repo.getUser(100), "That is not the correct client!");
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
        repo.deleteAllUser();

        //test adding a client into the database
        repo.addUser(c);
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
        repo.deleteAllUser();
        repo.addUser(c);

        //test delete an existing client from the database
        assertTrue(repo.existsById(clientId), "The client was not added!");
        repo.deleteUser(clientId);
        assertFalse(repo.existsById(clientId), "The client is still in the database!");
    }

    @Test
    public void testDeleteALlUser() {
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
        repo.deleteAllUser();
        attorneyRepo.deleteAllUser();
        repo.addUser(c);
        repo.addUser(c1);
        attorneyRepo.addUser(a);

        //test remove two users
        repo.deleteAllUser();
        assertFalse(repo.existsById(clientId), "Client c has not been removed!");
        assertFalse(repo.existsById(clientId2), "Client c1 has not been removed!");

        //set up
        repo.addUser(c);
        repo.addUser(c1);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all client
        repo.deleteAllUser();
        Long count = em.createQuery("SELECT COUNT(c) FROM Client c", Long.class).getSingleResult();
        assertEquals(0, count, "The database still has saved client objects!");
        //test attorney a still in database
        assertTrue(attorneyRepo.existsById(150), "Attorney a is no longer in the database");

    }

    @Test
    public void testExistsByName() {
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
        Attorney a = new Attorney(150, "obo", "obo.obo@hotmail.com", "obo321",
                "NO", "A6AM1M");
        ClientRepository repo = new ClientRepository();
        AttorneyRepository attorneyRepo = new AttorneyRepository();

        //set up
        repo.deleteAllUser();
        attorneyRepo.deleteAllUser();
        repo.addUser(c);
        attorneyRepo.addUser(a);

        //test username exists
        assertTrue(repo.existsByName("bob"), "The username does not exist!");
        //test username does not exist
        assertFalse(repo.existsByName("John"), "The username exists!");
        //test username belongs to an attorney entity
        assertFalse(repo.existsByName("obo"), "The username exists!");
    }

    @Test
    public void getAllQuestionById() {
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
        Question q = new Question(15, "theft", "hi", LocalDate.now(), 10,
                LocalDate.now());
        Question q1 = new Question(25, "theft", "hi", LocalDate.now(), clientId,
                LocalDate.now());
        Question q2 = new Question(35, "theft", "hi", LocalDate.now(), clientId,
                LocalDate.now());
        ClientRepository repo = new ClientRepository();
        QuestionRepo qRepo = new QuestionRepo();

        //set up
        repo.deleteAllUser();
        qRepo.deleteAllQuestion();
        repo.addUser(c);
        qRepo.saveQuestion(q);
        qRepo.saveQuestion(q1);
        qRepo.saveQuestion(q2);
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q1);
        expectedList.add(q2);

        //test get all Post by Question
        assert expectedList.equals(repo.getAllQuestionById(clientId));
    }

    @Test
    public void testUpdateQuestionList() {
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
        Question q = new Question(6, "theft", "hi", LocalDate.now(), clientId, LocalDate.now());
        ClientRepository repo = new ClientRepository();
        //set up
        repo.deleteAllUser();
        repo.addUser(c);

        //test updating list
        repo.updateQuestionList(clientId, q);
        ArrayList<Question> expectedList = new ArrayList<>();
        expectedList.add(q);
        assert expectedList.equals(repo.getUser(clientId).getQuestionsList());
    }

}