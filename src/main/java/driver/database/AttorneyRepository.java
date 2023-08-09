package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.*;

import javax.jdo.JDOHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

/**
 * This class represents managing Attorney entities in the database.
 */
public class AttorneyRepository extends UserRepository<Attorney> implements AttorneyGateway {

    /**
     * Constructs an AttorneyRepository instance.
     * Initializes the superclass with the Attorney class.
     */
    public AttorneyRepository() {
        super(Attorney.class);
    }

    /**
     * Retrieves an Attorney entity with the specified ID from the database.
     *
     * @param id The ID of the Attorney entity to retrieve.
     * @return The Attorney entity associated with the specified ID, or null if not found.
     */
    @Override
    public Attorney get(int id) {
        return (Attorney) super.get(id);
    }

    /**
     * Clears all recommendations associated with Attorneys in the database.
     * This method removes all recommendations from each Attorney entity.
     */
    @Override
    public void clearAllRecommendations() {
        EntityManager em = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Attorney> list = getAll();
        ArrayList<Question> emptyList = new ArrayList<>();
        try {
            transaction.begin();
            for (Attorney a : list) {
                a.setRecommendations(emptyList);
                JDOHelper.makeDirty(a, "recommendations");
            }
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

    /**
     * Adds a recommendation to an Attorney entity in the database.
     *
     * @param Userid The ID of the Attorney entity to which the recommendation will be added.
     * @param question The Question entity representing the recommendation.
     */
    @Override
    public void addRecommendation(int Userid, Question question) {
        EntityManager em = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        Attorney a = em.find(Attorney.class, Userid);
        try {
            transaction.begin();
            a.addRecommendation(question);
            JDOHelper.makeDirty(a, "recommendations");
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
}