package usecases.session;

/**
 * This class manages the life-cycle of the user session.
 */
public class SessionManager {

    // The current active user session.
    private static UserSession currentSession = null;

    public static void setSession(UserSession session) {
        currentSession = session;
    }

    public static UserSession getSession() {
        return currentSession;
    }

    public static void clearSession() {
        currentSession = null;
    }
}



