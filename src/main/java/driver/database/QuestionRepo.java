package driver.database;

import entity.Post;
import entity.Question;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.time.LocalDate;
import java.util.List;

public class QuestionRepo implements QuestionGateway {

    @Override
    public void saveQuestion(Question question) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        try {
            transaction.begin();
            entityManager.persist(question);
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
    public boolean checkExistsByName(int questionId){
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            Question exists = entityManager.find(Question.class, questionId);
            return (exists != null);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public Question getQuestion(int questionId) {
        EntityManager entityManager = DatabaseConnection.getEntityManager();
        try {
            return entityManager.find(Question.class, questionId);
        } finally {
            entityManager.close();
        }
    }

    @Override
    public List<Question> getAllQuestion() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q", Question.class).getResultList();
        } finally {
            em.close();
        }
    }

    @Override
    public List<Question> getNotTakenQuestion() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            return em.createQuery("SELECT q FROM Question q WHERE q.isTaken = false", Question.class)
                    .getResultList();
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
    public void updatePosts(int questionId, Post post) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.addPosts(post);
            //JDOHelper.makeDirty(question, "posts");
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
    public void deleteQuestion(int questionId) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            Question question = getQuestion(questionId);
            em.remove(question);
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
    public void deleteAllQuestion() {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Question q").executeUpdate();
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
