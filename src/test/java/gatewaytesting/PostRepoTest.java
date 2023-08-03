package gatewaytesting;

import gateway.DatabaseConnection;
import gateway.PostRepo;
import org.junit.jupiter.api.Test;
import questionentities.Post;

import javax.persistence.EntityManager;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class PostRepoTest {

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
        assertTrue(repo.checkExistsById(postId), "Post is not saved into the database!");
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
    public void testCheckExistsById() {
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

        //test the id exists
        assertTrue(repo.checkExistsById(postId), "The id does not exist!");
        //test the id does not exist
        assertFalse(repo.checkExistsById(15), "The id exists!");
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
        assertTrue(repo.checkExistsById(postId), "The post was not added!");
        repo.deletePost(postId);
        assertFalse(repo.checkExistsById(postId), "the post was not deleted!");
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
