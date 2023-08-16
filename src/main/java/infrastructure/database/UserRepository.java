package infrastructure.database;

import usecases.gateway.UserGateway;
import entities.Question;
import entities.user.User;
import javax.jdo.JDOHelper;
import java.util.List;

/**
 * This class represents managing User entities in the database.
 *
 * @param <T> The type of User entity managed by the repository.
 */
public class UserRepository<T extends User> extends GenericRepository<T> implements UserGateway<T> {

    /**
     * Constructs a UserRepository instance.
     *
     * @param entityType The class representing the type of User entity managed by the repository.
     */
    public UserRepository(Class<T> entityType) {
        super(entityType);
    }

    @Override
    public boolean existsByName(String inputUserName) {
        return executeTransactionWithResult(entityManager -> {
            List<T> users = entityManager.createQuery("SELECT u FROM " + entityType.getName() +
                    " u WHERE u.name = :name", entityType).setParameter("name", inputUserName).getResultList();
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
