package gatewaytesting;

import businessrule.requestmodel.RegistrationData;
import driver.database.*;
import entity.Post;
import entity.factory.AttorneyFactory;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entity.Question;
import entity.Attorney;

import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRepoTest {

    //Question
    final static int QUESTION_ID = 6;
    final static int QUESTION_ID1 = 16;
    final static LocalDate CREATE_AT = LocalDate.now();

    //Post
    final static int POST_ID = 10;
    final static int POST_ID1 = 20;
    final static int POST_ID2 = 30;

    //Attorney
    final static int ATTORNEY_ID = 150;
    final static int ATTORNEY_ID1 = 151;

    @BeforeAll
    public static void setUp() {
        //Question
        String type = "Theft";
        String title = "hi";
        int askedByClient = 16;
        int askedByClient1 = 61;
        LocalDate legalDeadLine = LocalDate.now();

        Question q = new Question(QUESTION_ID, type, title, CREATE_AT, askedByClient, legalDeadLine);
        Question q1 = new Question(QUESTION_ID1, type, title, CREATE_AT, askedByClient1, legalDeadLine);
        QuestionRepo repo = new QuestionRepo();

        repo.deleteAll();
        q1.setClose(true);
        q.setTaken(true);
        repo.save(q);
        repo.save(q1);

        //Post
        LocalDate postCreateAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        Post p = new Post(QUESTION_ID, POST_ID, postCreateAt, postText, belongsTo);
        Post p1 = new Post(QUESTION_ID, POST_ID1, postCreateAt, postText, belongsTo);
        Post p3 = new Post(QUESTION_ID1, POST_ID2, postCreateAt, postText, belongsTo);
        PostRepo pRepo = new PostRepo();

        pRepo.deleteAll();
        pRepo.save(p);
        pRepo.save(p1);
        pRepo.save(p3);

        //Attorney
        String name = "obo";
        String name2 = "joseph";
        String email = "obo.obo@hotmail.com";
        String password = "obo123321";
        String password2 = "obo123321";
        String stateAbb = "NO";
        String postalCode = "A6AM1M";

        RegistrationData registrationData = new RegistrationData(name, email, password, password2, stateAbb, postalCode);
        RegistrationData registrationData2 = new RegistrationData(name2, email, password, password2, stateAbb, postalCode);
        AttorneyFactory attorneyFactory = new AttorneyFactory();
        Attorney a  = attorneyFactory.createUser(registrationData);
        a.setUserId(ATTORNEY_ID);
        Attorney a1 = attorneyFactory.createUser(registrationData2);
        a1.setUserId(ATTORNEY_ID1);
        AttorneyRepository aRepo = new AttorneyRepository();

        aRepo.deleteAll();
        aRepo.save(a);
        aRepo.save(a1);
    }

    @Test
    public void testExistsById() {
        QuestionRepo repo = new QuestionRepo();
        //test checking existing question
        assertTrue(repo.existsById(QUESTION_ID), "The question does not exist!");
        //test checking question that does not exist
        assertFalse(repo.existsById(7), "The question exists!");
    }

    @Test
    public void testGetAllQuestion() {
        QuestionRepo repo = new QuestionRepo();
        Question q = repo.get(QUESTION_ID);
        Question q1 = repo.get(QUESTION_ID1);
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q);
        expectedList.add(q1);
        //test getting all questions
        assert expectedList.equals(repo.getAll());
        //test no questions in database
        repo.deleteAll();
        assertEquals(0, repo.getAll().size());

        //recover
        q1.setClose(true);
        q.setTaken(true);
        repo.save(q);
        repo.save(q1);
    }

    @Test
    public void testGetNotTakenQuestion() {
        QuestionRepo repo = new QuestionRepo();
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(repo.get(QUESTION_ID1));
        //test one taken question
        assert expectedList.equals(repo.getNotTakenQuestion());
    }

    @Test
    public void testGetNotClosedQuestion() {
        QuestionRepo repo = new QuestionRepo();
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(repo.get(QUESTION_ID));
        //test one taken question
        assert expectedList.equals(repo.getNotClosedQuestion());
    }

    @Test
    public void testGetAllPostByQuestion() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        List<Post> expectedList = new ArrayList<>();
        expectedList.add(pRepo.get(POST_ID));
        expectedList.add(pRepo.get(POST_ID1));
        //test get all Post by Question
        assert expectedList.equals(repo.getAllPostOfQuestion(QUESTION_ID));
    }

    @Test
    public void testUpdateIsTaken() {
        QuestionRepo repo = new QuestionRepo();
        //test update taken true
        repo.updateIsTaken(QUESTION_ID, true);
        assertTrue(repo.get(QUESTION_ID).isTaken(), "Taken is not updated to true!");
        //test update taken false
        repo.updateIsTaken(QUESTION_ID, false);
        assertFalse(repo.get(QUESTION_ID).isTaken(), "Taken is not updated to false!");
        //test update another question
        repo.updateIsTaken(QUESTION_ID1, true);
        assertFalse(repo.get(QUESTION_ID).isTaken(), "Taken is updated for q!");

        //recover
        repo.updateIsTaken(QUESTION_ID, true);
        repo.updateIsTaken(QUESTION_ID1, false);
    }

    @Test
    public void testUpdateTakenByAttorney() {
        QuestionRepo repo = new QuestionRepo();
        //test update taken by a
        repo.updateTakenByAttorney(QUESTION_ID, ATTORNEY_ID);
        assertEquals(ATTORNEY_ID, repo.get(QUESTION_ID).getTakenByAttorney(), "q is not taken by a!");
        //test update taken by a1
        repo.updateTakenByAttorney(QUESTION_ID, ATTORNEY_ID1);
        assertEquals(ATTORNEY_ID1, repo.get(QUESTION_ID).getTakenByAttorney(), "q1 is not taken by a!");
        assertNotEquals(ATTORNEY_ID, repo.get(QUESTION_ID).getTakenByAttorney(), "q is taken by a!");
    }

    @Test
    public void testUpdateIsClose() {
        QuestionRepo repo = new QuestionRepo();
        //test update is closed true
        repo.updateIsClose(QUESTION_ID, true);
        assertTrue(repo.get(QUESTION_ID).isClose(), "Question has not been updated!");
        // test update is closed false
        repo.updateIsClose(QUESTION_ID, false);
        assertFalse(repo.get(QUESTION_ID).isClose(), "Question has not been updated!");
        //test update another question
        repo.updateIsClose(QUESTION_ID1, true);
        assertFalse(repo.get(QUESTION_ID).isClose(), "This question was updated!");
    }

    @Test
    public void testUpdateRating() {
        QuestionRepo repo = new QuestionRepo();
        //test update is closed true
        repo.updateRating(QUESTION_ID, 1);
        assertEquals(1, repo.get(QUESTION_ID).getRating(), "Question has not been updated!");
        // test update is closed false
        repo.updateRating(QUESTION_ID, 0);
        assertEquals(0, repo.get(QUESTION_ID).getRating(), "Question has not been updated!");
        //test update another question
        repo.updateRating(QUESTION_ID1, 1);
        assertEquals(0, repo.get(QUESTION_ID).getRating(), "This question was updated!");
    }

    @Test
    public void testUpdateTakenAt() {
        LocalDate changeDate = LocalDate.now().minusDays(1);
        QuestionRepo repo = new QuestionRepo();
        //test successful update takenAt
        repo.updateTakenAt(QUESTION_ID, changeDate);
        assertEquals(changeDate, repo.get(QUESTION_ID).getTakenAt(), "Question has not been updated!");
        // test updating takenAt back to createAt
        repo.updateTakenAt(QUESTION_ID, CREATE_AT);
        assertEquals(CREATE_AT, repo.get(QUESTION_ID).getTakenAt(), "Question has not been updated!");
        //test update another question
        repo.updateTakenAt(QUESTION_ID1, changeDate);
        assertEquals(CREATE_AT, repo.get(QUESTION_ID).getTakenAt(), "This question was updated!");
    }

    @Test
    public void testUpdatePosts() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        //test updating list
        repo.updatePosts(QUESTION_ID, pRepo.get(POST_ID));
        ArrayList<Post> expectedList = new ArrayList<>();
        expectedList.add(pRepo.get(POST_ID));
        assert expectedList.equals(repo.get(QUESTION_ID).getPosts());
    }

    @AfterAll
    public static void tearDown() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        AttorneyRepository aRepo = new AttorneyRepository();
        repo.deleteAll();
        pRepo.deleteAll();
        aRepo.deleteAll();
    }

    @Test
    public void testSaveQuestion() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        QuestionRepo repo = new QuestionRepo();

        //set up
        repo.delete(questionId);

        //test saving a question into the database
        q.setTaken(true);
        repo.save(q);
        assertTrue(repo.existsById(questionId), "Question is not saved into the database!");
    }

    @Test
    public void testGetQuestion() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        QuestionRepo repo = new QuestionRepo();

        //set up
        repo.delete(questionId);
        q.setTaken(true);
        repo.save(q);

        //test getting the correct post
        assertEquals(q, repo.get(questionId), "That is not the correct question!");
    }

    @Test
    public void testDeleteQuestion() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        Question q1 = new Question(16, type, title, createAt, 61, legalDeadLine);
        QuestionRepo repo = new QuestionRepo();

        //set up
        repo.deleteAll();
        repo.save(q);

        //test deleting an existing question from the database
        assertTrue(repo.existsById(questionId), "The post was not added!");
        repo.delete(questionId);
        assertFalse(repo.existsById(questionId), "the post was not deleted!");

        //recover
        repo.deleteAll();
        q1.setClose(true);
        q.setTaken(true);
        repo.save(q);
        repo.save(q1);
    }

    @Test
    public void testDeleteAllQuestion() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        Question q1 = new Question(16, type, title, createAt, 61, legalDeadLine);
        QuestionRepo repo = new QuestionRepo();

        //set up
        repo.deleteAll();
        repo.save(q);
        repo.save(q1);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all posts
        repo.deleteAll();
        Long count = em.createQuery("SELECT COUNT(q) FROM Question q", Long.class).getSingleResult();
        assertEquals(0, count, "The database still have saved post objects!");
        //test remove no posts
        repo.deleteAll();
        Long count1 = em.createQuery("SELECT COUNT(q) FROM Question q", Long.class).getSingleResult();
        assertEquals(0, count1, "The database still have saved post objects!");

        //recover
        repo.deleteAll();
        q1.setClose(true);
        q.setTaken(true);
        repo.save(q);
        repo.save(q1);
    }

}
