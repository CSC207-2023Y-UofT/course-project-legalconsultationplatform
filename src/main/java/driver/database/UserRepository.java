package driver.database;

import businessrule.gateway.UserGateway;
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
            List<T> users = entityManager.createQuery("SELECT u FROM " + entityType.getSimpleName() +
                    " u WHERE u.name =: name", entityType).setParameter("name", inputUserName).getResultList();
            return !users.isEmpty();
        });
    }

    @Override
    public void updateQuestionList(int id, Question question) {
        executeTransaction(entityManager -> {
            User u = entityManager.find(entityType, id);
            u.addQuestion(question);
            JDOHelper.makeDirty(u, "questionsList");
        });
    }

    @Override
    public User get(int id) {
        return (User) super.get(id);
    }

}
