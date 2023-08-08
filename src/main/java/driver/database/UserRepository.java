package driver.database;

import businessrule.gateway.UserGateway;
import entity.Attorney;
import entity.Question;
import entity.User;

import javax.jdo.JDOHelper;
import java.util.List;

public class UserRepository<T extends User> extends GenericRepository<T> implements UserGateway<T> {

    public UserRepository(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public boolean existsByName(String inputUserName) {
        return executeTransactionWithResult(entityManager -> {
            List<Attorney> users = entityManager.createQuery("SELECT a FROM Attorney a WHERE a.name =: name",
                            Attorney.class).setParameter("name", inputUserName).getResultList();
            return !users.isEmpty();
        });
    }

    @Override
    public void updateQuestionList(int id, Question question) {
        executeTransaction(entityManager -> {
            User u = entityManager.find(User.class, id);
            u.addQuestion(question);
            JDOHelper.makeDirty(u, "questionsList");
        });
    }

    @Override
    public T get(int id) {
        return (T) super.get(id);
    }

}
