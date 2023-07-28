package gateway;

import questionentities.Post;
import questionentities.Question;
import userentities.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.List;

public class QuestionRepo implements QuestionGateway{
    DatabaseConnection databaseConnection;

    public QuestionRepo(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

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
        return null;
    }

    @Override
    public List<Question> getNotTakenQuestion() {
        return null;
    }

    @Override
    public List<Question> getNotClosedQuestion() {
        return null;
    }

    @Override
    public void updateIsTaken(int questionId, boolean isTaken) {
        EntityManager em = DatabaseConnection.getEntityManager();
        Question question = em.find(Question.class, questionId);
        try {
            em.getTransaction().begin();
            question.setTaken(isTaken);
            em.getTransaction().commit();
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
            em.getTransaction().commit();
        } finally {
            em.close();
        }
    }
}
