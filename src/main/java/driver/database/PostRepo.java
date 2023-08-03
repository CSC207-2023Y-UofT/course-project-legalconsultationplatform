package driver.database;

import entity.Post;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class PostRepo implements PostGateway{

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

    @Override
    public void deletePost(int postId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Post post = em.find(Post.class, postId);
            em.remove(post);
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public void deleteAllPost() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Post p").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
