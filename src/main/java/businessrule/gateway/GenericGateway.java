package businessrule.gateway;

import java.util.List;

public interface GenericGateway<T> {

    void save(T entity);
    Object get(int id);
    boolean existsById(int id);
    void delete(int id);
    void deleteAll();
    List<T> getAll();

}
