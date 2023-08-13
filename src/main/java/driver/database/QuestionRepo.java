package driver.database;

import businessrule.gateway.QuestionGateway;
import entity.Client;
import entity.Post;
import entity.Question;
import entity.User;

import javax.jdo.JDOHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents managing Question entities in the database.
 */
public class QuestionRepo extends GenericRepository<Question> implements QuestionGateway {

    /**
     * Constructs a QuestionRepo instance.
     * Initializes the superclass with the Question class.
     */
    public QuestionRepo() {
        super(Question.class);
    }

    @Override
    public List<Question> getNotTakenQuestion() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q WHERE q.isTaken = false", Question.class)
                    .getResultList();
        } catch(Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Question> getNotClosedQuestion() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q WHERE q.isClose = false", Question.class)
                    .getResultList();
        } catch(Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Post> getAllPostOfQuestion(int questionId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT p FROM Post p WHERE p.questionId =: questionId", Post.class)
                    .setParameter("questionId", questionId).getResultList();
        } catch(Exception e) {
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }

    @Override
    public void updateIsTaken(int questionId, boolean isTaken) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setTaken(isTaken);
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
    public void updateTakenByAttorney(int questionId, int attorneyId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setTakenByAttorney(attorneyId);
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
    public void updateIsClose(int questionId, boolean isClose) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setClose(isClose);
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
    public void updateRating(int questionId, int rating) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setRating(rating);
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
    public void updateTakenAt(int questionId, LocalDate time) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setTakenAt(time);
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
    public void updatePosts(int questionId, Post post) {
        EntityManager em = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Question q = em.find(Question.class, questionId);
        try {
            transaction.begin();
            q.addPosts(post);
            JDOHelper.makeDirty(q, "posts");
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    @Override
    public Question get(int id) {
        return (Question) super.get(id);
    }

}
