package gatewaytesting;

import driver.database.*;
import entity.Post;
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

        repo.deleteAllQuestion();
        q1.setClose(true);
        q.setTaken(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);

        //Post
        LocalDate postCreateAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        Post p = new Post(QUESTION_ID, POST_ID, postCreateAt, postText, belongsTo);
        Post p1 = new Post(QUESTION_ID, POST_ID1, postCreateAt, postText, belongsTo);
        Post p3 = new Post(QUESTION_ID1, POST_ID2, postCreateAt, postText, belongsTo);
        PostRepo pRepo = new PostRepo();

        pRepo.deleteAllPost();
        pRepo.savePost(p);
        pRepo.savePost(p1);
        pRepo.savePost(p3);

        //Attorney
        String name = "obo";
        String email = "obo.obo@hotmail.com";
        String password = "obo321";
        String stateAbb = "NO";
        String postalCode = "A6AM1M";

        Attorney a = new Attorney(ATTORNEY_ID, name, email, password, stateAbb, postalCode);
        Attorney a1 = new Attorney(ATTORNEY_ID1, name, email, password, stateAbb, postalCode);
        AttorneyRepository aRepo = new AttorneyRepository();

        aRepo.deleteAllUser();
        aRepo.addUser(a);
        aRepo.addUser(a1);
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
        Question q = repo.getQuestion(QUESTION_ID);
        Question q1 = repo.getQuestion(QUESTION_ID1);
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q);
        expectedList.add(q1);
        //test getting all questions
        assert expectedList.equals(repo.getAllQuestion());
        //test no questions in database
        repo.deleteAllQuestion();
        assertEquals(0, repo.getAllQuestion().size());

        //recover
        q1.setClose(true);
        q.setTaken(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
    }

    @Test
    public void testGetNotTakenQuestion() {
        QuestionRepo repo = new QuestionRepo();
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(repo.getQuestion(QUESTION_ID1));
        System.out.println(expectedList);
        System.out.println(repo.getNotTakenQuestion());
        System.out.println(repo.getQuestion(QUESTION_ID).isTaken());
        System.out.println(repo.getQuestion(QUESTION_ID1).isTaken());
        //test one taken question
        assert expectedList.equals(repo.getNotTakenQuestion());
    }

    @Test
    public void testGetNotClosedQuestion() {
        QuestionRepo repo = new QuestionRepo();
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(repo.getQuestion(QUESTION_ID));
        //test one taken question
        assert expectedList.equals(repo.getNotClosedQuestion());
    }

    @Test
    public void testGetAllPostByQuestion() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        List<Post> expectedList = new ArrayList<>();
        expectedList.add(pRepo.getPost(POST_ID));
        expectedList.add(pRepo.getPost(POST_ID1));

        //test get all Post by Question
        assert expectedList.equals(repo.getAllPostOfQuestion(QUESTION_ID));
    }

    @Test
    public void testUpdateIsTaken() {
        QuestionRepo repo = new QuestionRepo();
        //test update taken true
        repo.updateIsTaken(QUESTION_ID, true);
        assertTrue(repo.getQuestion(QUESTION_ID).isTaken(), "Taken is not updated to true!");
        //test update taken false
        repo.updateIsTaken(QUESTION_ID, false);
        assertFalse(repo.getQuestion(QUESTION_ID).isTaken(), "Taken is not updated to false!");
        //test update another question
        repo.updateIsTaken(QUESTION_ID1, true);
        assertFalse(repo.getQuestion(QUESTION_ID).isTaken(), "Taken is updated for q!");

        //recover
        repo.updateIsTaken(QUESTION_ID, true);
        repo.updateIsTaken(QUESTION_ID1, false);
    }

    @Test
    public void testUpdateTakenByAttorney() {
        QuestionRepo repo = new QuestionRepo();
        //test update taken by a
        repo.updateTakenByAttorney(QUESTION_ID, ATTORNEY_ID);
        assertEquals(ATTORNEY_ID, repo.getQuestion(QUESTION_ID).getTakenByAttorney(), "q is not taken by a!");
        //test update taken by a1
        repo.updateTakenByAttorney(QUESTION_ID, ATTORNEY_ID1);
        assertEquals(ATTORNEY_ID1, repo.getQuestion(QUESTION_ID).getTakenByAttorney(), "q1 is not taken by a!");
        assertNotEquals(ATTORNEY_ID, repo.getQuestion(QUESTION_ID).getTakenByAttorney(), "q is taken by a!");
    }

    @Test
    public void testUpdateIsClose() {
        QuestionRepo repo = new QuestionRepo();
        //test update is closed true
        repo.updateIsClose(QUESTION_ID, true);
        assertTrue(repo.getQuestion(QUESTION_ID).isClose(), "Question has not been updated!");
        // test update is closed false
        repo.updateIsClose(QUESTION_ID, false);
        assertFalse(repo.getQuestion(QUESTION_ID).isClose(), "Question has not been updated!");
        //test update another question
        repo.updateIsClose(QUESTION_ID1, true);
        assertFalse(repo.getQuestion(QUESTION_ID).isClose(), "This question was updated!");
    }

    @Test
    public void testUpdateRating() {
        QuestionRepo repo = new QuestionRepo();
        //test update is closed true
        repo.updateRating(QUESTION_ID, 1);
        assertEquals(1, repo.getQuestion(QUESTION_ID).getRating(), "Question has not been updated!");
        // test update is closed false
        repo.updateRating(QUESTION_ID, 0);
        assertEquals(0, repo.getQuestion(QUESTION_ID).getRating(), "Question has not been updated!");
        //test update another question
        repo.updateRating(QUESTION_ID1, 1);
        assertEquals(0, repo.getQuestion(QUESTION_ID).getRating(), "This question was updated!");
    }

    @Test
    public void testUpdateTakenAt() {
        LocalDate changeDate = LocalDate.now().minusDays(1);
        QuestionRepo repo = new QuestionRepo();
        //test successful update takenAt
        repo.updateTakenAt(QUESTION_ID, changeDate);
        assertEquals(changeDate, repo.getQuestion(QUESTION_ID).getTakenAt(), "Question has not been updated!");
        // test updating takenAt back to createAt
        repo.updateTakenAt(QUESTION_ID, CREATE_AT);
        assertEquals(CREATE_AT, repo.getQuestion(QUESTION_ID).getTakenAt(), "Question has not been updated!");
        //test update another question
        repo.updateTakenAt(QUESTION_ID1, changeDate);
        assertEquals(CREATE_AT, repo.getQuestion(QUESTION_ID).getTakenAt(), "This question was updated!");
    }

    @Test
    public void testUpdatePosts() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        //test updating list
        repo.updatePosts(QUESTION_ID, pRepo.getPost(POST_ID));
        ArrayList<Post> expectedList = new ArrayList<>();
        expectedList.add(pRepo.getPost(POST_ID));
        assert expectedList.equals(repo.getQuestion(QUESTION_ID).getPosts());
    }

    @AfterAll
    public static void tearDown() {
        QuestionRepo repo = new QuestionRepo();
        PostRepo pRepo = new PostRepo();
        AttorneyRepository aRepo = new AttorneyRepository();
        repo.deleteAllQuestion();
        pRepo.deleteAllPost();
        aRepo.deleteAllUser();
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
        repo.deleteQuestion(questionId);

        //test saving a question into the database
        q.setTaken(true);
        repo.saveQuestion(q);
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
        repo.deleteQuestion(questionId);
        q.setTaken(true);
        repo.saveQuestion(q);

        //test getting the correct post
        assertEquals(q, repo.getQuestion(questionId), "That is not the correct question!");
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
        repo.deleteAllQuestion();
        repo.saveQuestion(q);

        //test deleting an existing question from the database
        assertTrue(repo.existsById(questionId), "The post was not added!");
        repo.deleteQuestion(questionId);
        assertFalse(repo.existsById(questionId), "the post was not deleted!");

        //recover
        repo.deleteAllQuestion();
        q1.setClose(true);
        q.setTaken(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
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
        repo.deleteAllQuestion();
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all posts
        repo.deleteAllQuestion();
        Long count = em.createQuery("SELECT COUNT(q) FROM Question q", Long.class).getSingleResult();
        assertEquals(0, count, "The database still have saved post objects!");
        //test remove no posts
        repo.deleteAllQuestion();
        Long count1 = em.createQuery("SELECT COUNT(q) FROM Question q", Long.class).getSingleResult();
        assertEquals(0, count1, "The database still have saved post objects!");

        //recover
        repo.deleteAllQuestion();
        q1.setClose(true);
        q.setTaken(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
    }

}
