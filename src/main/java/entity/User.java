package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.List;
@Entity
public abstract class User {
    @Id
    int userId;
    public abstract int getUserId();
    public abstract String getUserName();
    public abstract String getPassword();
    public abstract String getEmail();
}
