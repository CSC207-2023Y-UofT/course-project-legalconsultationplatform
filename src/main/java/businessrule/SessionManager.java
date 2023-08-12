package businessrule;

import javax.persistence.criteria.CriteriaBuilder;
import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    // A map to hold multiple user sessions, indexed by user IDs.
    private static Map<Integer, UserSession> sessions = new HashMap<>();


    public static void setSession(int userId, UserSession session) {
        sessions.put(userId, session);
    }

    public static UserSession getSession(int userId) {
        return sessions.get(userId);
    }

    public static void clearSession(int userId) {
        sessions.remove(userId);
    }
}


