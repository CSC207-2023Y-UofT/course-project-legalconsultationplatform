package driver.database;

import businessrule.gateway.ClientGateway;
import entity.Client;

import javax.persistence.*;
import java.util.List;

public class ClientRepository extends UserRepository implements ClientGateway {

    public boolean existsByUsername(String username) {
        EntityManager em = DatabaseConnection.getEntityManager();
        try {
            List<Client> users = em.createQuery("SELECT u FROM Client u WHERE u.userName =: username", Client.class).
                    setParameter("username", username).getResultList();
            return !users.isEmpty();
        } finally {
            em.close();
        }
    }

    @Override
    public boolean existsByName(String inputUserName) {
        return false;
    }
}

