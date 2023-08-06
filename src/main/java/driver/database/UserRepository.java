package driver.database;

import entity.User;

public interface UserRepository<T> {
    boolean existsById(int id);
    void addUser(User entity);

}