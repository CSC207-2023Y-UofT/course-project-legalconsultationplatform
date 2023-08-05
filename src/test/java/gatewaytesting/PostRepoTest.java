package gatewaytesting;

import driver.database.DatabaseConnection;
import driver.database.PostRepo;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import entity.Post;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PostRepoTest {

    //Post
    final static int POST_ID = 10;

    //Question
    final static int QUESTION_ID = 10;

    @BeforeAll
    public static void setUp() {
        LocalDate createdAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        //constructors
        Post p = new Post(QUESTION_ID, POST_ID, createdAt, postText, belongsTo);
        PostRepo repo = new PostRepo();

        //set up
        repo.deleteAllPost();
        repo.savePost(p);
    }

    @Test
    public void testCheckExistsById() {
        PostRepo repo = new PostRepo();
        //test the id exists
        assertTrue(repo.existsById(POST_ID), "The id does not exist!");
        //test the id does not exist
        assertFalse(repo.existsById(15), "The id exists!");
    }

    @AfterAll
    public static void tearDown() {
        PostRepo repo = new PostRepo();
        repo.deleteAllPost();
    }

    @Test
    public void testSavePost() {
        int questionId = 15;
        int postId = 10;
        LocalDate createdAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        //constructors
        Post p = new Post(questionId, postId, createdAt, postText, belongsTo);
        PostRepo repo = new PostRepo();

        //set up
        repo.deleteAllPost();

        //test saving a post into the database
        repo.savePost(p);
        assertTrue(repo.existsById(postId), "Post is not saved into the database!");
    }

    @Test
    public void testGetPost() {
        int questionId = 15;
        int postId = 10;
        LocalDate createdAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        //constructors
        Post p = new Post(questionId, postId, createdAt, postText, belongsTo);
        PostRepo repo = new PostRepo();

        //set up
        repo.deleteAllPost();
        repo.savePost(p);

        //test getting the correct post
        assertEquals(p, repo.getPost(postId), "That is not the correct post!");
    }

    @Test
    public void testDeletePost() {
        int questionId = 15;
        int postId = 10;
        LocalDate createdAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        //constructors
        Post p = new Post(questionId, postId, createdAt, postText, belongsTo);
        PostRepo repo = new PostRepo();

        //set up
        repo.deleteAllPost();
        repo.savePost(p);

        //test deleting an existing client from the database
        assertTrue(repo.existsById(postId), "The post was not added!");
        repo.deletePost(postId);
        assertFalse(repo.existsById(postId), "the post was not deleted!");
    }

    @Test
    public void testDeleteAllPost() {
        int questionId = 15;
        int postId = 10;
        LocalDate createdAt = LocalDate.now();
        String postText = "hello!";
        int belongsTo = 100;

        //constructors
        Post p = new Post(questionId, postId, createdAt, postText, belongsTo);
        PostRepo repo = new PostRepo();

        //set up
        repo.deleteAllPost();
        repo.savePost(p);
        EntityManager em = DatabaseConnection.getEntityManager();

        //test remove all posts
        repo.deleteAllPost();
        Long count = em.createQuery("SELECT COUNT(p) FROM Post p", Long.class).getSingleResult();
        assertEquals(0, count, "The database still have saved post objects!");
    }

}
