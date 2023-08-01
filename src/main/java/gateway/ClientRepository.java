package gateway;

import questionentities.Post;
import questionentities.Question;
import userentities.Client;
import userentities.User;

import javax.persistence.*;
import javax.xml.crypto.Data;
import java.util.Date;
import java.util.List;

public class ClientRepository extends UserRepository implements ClientGateway{

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

}

