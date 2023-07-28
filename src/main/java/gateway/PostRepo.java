package gateway;

import questionentities.Post;
import userentities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PostRepo implements PostGateway{
    DatabaseConnection databaseConnection;

    public PostRepo(DatabaseConnection databaseConnection){
        this.databaseConnection = databaseConnection;
    }
    @Override
    public void savePost(Post post) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(post);
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Post getPost(int postId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return entityManager.find(Post.class, postId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public boolean checkExistsById(int postId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            Post exists = entityManager.find(Post.class, postId);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }
}
