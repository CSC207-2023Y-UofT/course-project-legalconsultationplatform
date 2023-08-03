package gatewaytesting;

import gateway.AttorneyRepository;
import gateway.ClientRepository;
import gateway.DatabaseConnection;
import org.junit.jupiter.api.Test;
import userentities.Attorney;
import userentities.Client;

import javax.persistence.EntityManager;

import static org.junit.jupiter.api.Assertions.*;

public class AttorneyRepositoryTest {

    @Test
    public void testExistsById(){
        int attorneyId = 50;
        String attorneyUsername = "yao";
        String attorneyEmail = "yao.yao@gmail.com";
        String attorneyPassword = "yao123";
        String attorneyState = "ON";
        String attorneyPostalCode = "M8MO1P";

        //constructors
        Attorney a = new Attorney(attorneyId, attorneyUsername, attorneyEmail, attorneyPassword,
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
        clientRepo.addUser(c);

        //test id exists
        assertTrue(repo.existsById(attorneyId), "The id does not exist!");
        //test id does not exist
        assertFalse(repo.existsById(75), "The id exists!");
        //test id belongs to a client
        assertFalse(repo.existsById(100), "The id exists!");
    }

    @Test
    public void testGetUser() {
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

        //test get the correct attorney
        assertEquals(a, repo.getUser(50), "That is not the correct client!");
    }

    @Test
    public void testUpdateQuestionList() {

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
        assertTrue(repo.existsById(50), "The attorney is not added!");
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
    }

    @Test
    public void testDeleteALlUser() {
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
        //test attorney a still in database
        assertTrue(clientRepo.existsById(100), "Attorney a is no longer in the database");

    }

}
