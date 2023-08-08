package driver.database;

import businessrule.gateway.AttorneyGateway;
import entity.*;

import javax.jdo.JDOHelper;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.ArrayList;
import java.util.List;

public class AttorneyRepository extends UserRepository<Attorney> implements AttorneyGateway {

    public AttorneyRepository() {
        super(Attorney.class);
    }

    @Override
    public Attorney get(int id) {
        return (Attorney) super.get(id);
    }

    @Override
    public void clearAllRecommendations() {
        EntityManager em = DatabaseConnection.getEntityManager();
        EntityTransaction transaction = em.getTransaction();
        List<Attorney> list = getAllAttorney();
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
