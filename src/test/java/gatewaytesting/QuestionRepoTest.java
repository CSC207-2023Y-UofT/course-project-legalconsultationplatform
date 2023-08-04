package gatewaytesting;

import driver.database.AttorneyRepository;
import driver.database.QuestionRepo;
import entity.Post;
import org.junit.jupiter.api.Test;
import entity.Question;
import entity.Attorney;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class QuestionRepoTest {

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
        repo.deleteAllQuestion();

        //test saving a question into the database
        repo.saveQuestion(q);
        assertTrue(repo.checkExistsByName(questionId), "Question is not saved into the database!");
    }

    @Test
    public void testCheckExistsByName() {
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
        repo.deleteAllQuestion();
        repo.saveQuestion(q);

        //test checking existing question
        assertTrue(repo.checkExistsByName(questionId), "The question does not exist!");
        //test checking question that does not exist
        assertFalse(repo.checkExistsByName(7), "The question exists!");
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
        repo.deleteAllQuestion();
        repo.saveQuestion(q);

        //test getting the correct post
        assertEquals(q, repo.getQuestion(questionId), "That is not the correct question!");
    }

    @Test
    public void testGetAllQuestion() {
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
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q);
        expectedList.add(q1);

        //test getting all questions
        assert expectedList.equals(repo.getAllQuestion());
        //test no questions in database
        repo.deleteAllQuestion();
        assertEquals(0, repo.getAllQuestion().size());
    }

    @Test
    public void testGetNotTakenQuestion() {
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
        q.setTaken(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q1);

        //test one taken question
        assert expectedList.equals(repo.getNotTakenQuestion());
    }

    @Test
    public void testGetNotClosedQuestion() {
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
        q1.setClose(true);
        repo.saveQuestion(q);
        repo.saveQuestion(q1);
        List<Question> expectedList = new ArrayList<>();
        expectedList.add(q);

        //test one taken question
        assert expectedList.equals(repo.getNotClosedQuestion());
    }

    @Test
    public void testUpdateIsTaken() {
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

        //test update taken true
        repo.updateIsTaken(6, true);
        assertTrue(repo.getQuestion(6).isTaken(), "Taken is not updated to true!");
        //test update taken false
        repo.updateIsTaken(6, false);
        assertFalse(repo.getQuestion(6).isTaken(), "Taken is not updated to false!");
        //test update another question
        repo.updateIsTaken(16, true);
        assertFalse(repo.getQuestion(6).isTaken(), "Taken is updated for q!");
    }

    @Test
    public void testUpdateTakenByAttorney() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        Attorney a = new Attorney(150, "obo", "obo.obo@hotmail.com", "obo321",
                "NO", "A6AM1M");
        Attorney a1 = new Attorney(151, "obo", "obo.obo@hotmail.com", "obo321",
                "NO", "A6AM1M");
        QuestionRepo repo = new QuestionRepo();
        AttorneyRepository aRepo = new AttorneyRepository();

        //set up
        repo.deleteAllQuestion();
        aRepo.deleteAllUser();
        aRepo.addUser(a);
        aRepo.addUser(a1);
        repo.saveQuestion(q);

        //test update taken by a
        repo.updateTakenByAttorney(6, 150);
        assertEquals(150, repo.getQuestion(6).getTakenByAttorney(), "q is not taken by a!");
        //test update taken by a1
        repo.updateTakenByAttorney(6, 151);
        assertEquals(151, repo.getQuestion(6).getTakenByAttorney(), "q1 is not taken by a!");
        assertNotEquals(150, repo.getQuestion(6).getTakenByAttorney(), "q is taken by a!");
    }

    @Test
    public void testUpdateIsClose() {
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

        //test update is closed true
        repo.updateIsClose(6, true);
        assertTrue(repo.getQuestion(6).isClose(), "Question has not been updated!");
        // test update is closed false
        repo.updateIsClose(6, false);
        assertFalse(repo.getQuestion(6).isClose(), "Question has not been updated!");
        //test update another question
        repo.updateIsClose(16, true);
        assertFalse(repo.getQuestion(6).isClose(), "This question was updated!");
    }

    @Test
    public void testUpdateRating() {
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

        //test update is closed true
        repo.updateRating(6, 1);
        assertEquals(1, repo.getQuestion(6).getRating(), "Question has not been updated!");
        // test update is closed false
        repo.updateRating(6, 0);
        assertEquals(0, repo.getQuestion(6).getRating(), "Question has not been updated!");
        //test update another question
        repo.updateRating(16, 1);
        assertEquals(0, repo.getQuestion(6).getRating(), "This question was updated!");
    }

    @Test
    public void testUpdatePosts() {
        int questionId = 6;
        String type = "Theft";
        String title = "hi";
        LocalDate createAt = LocalDate.now();
        int askedByClient = 66;
        LocalDate legalDeadLine = LocalDate.now();

        //constructors
        Question q = new Question(questionId, type, title, createAt, askedByClient, legalDeadLine);
        Post p = new Post(questionId, 510, createAt, "hello!", 66);
        QuestionRepo repo = new QuestionRepo();

        //set up
        repo.deleteAllQuestion();
        repo.saveQuestion(q);

        //test updating posts
        repo.updatePosts(questionId, p);
        ArrayList<Post> expectedList = new ArrayList<>();
        expectedList.add(p);
        System.out.println(expectedList);
        System.out.println(q.getPosts());
        System.out.println(repo.getQuestion(questionId).getPosts());
        assert expectedList.equals(repo.getQuestion(questionId).getPosts());
    }

}
