package infrastructure.database;

import entities.ApplicationException;
import entities.user.User;
import usecases.gateway.UserGateway;

/**
 * This class represents for creating instances of UserGateway based on the user's role.
 */
public class UserGatewayFactory {

    /**
     * Creates a UserGateway based on the user's role.
     *
     * @param userId The ID of the user for whom the gateway is to be created.
     * @return An instance of UserGateway for the specified user.
     */
    public UserGateway<? extends User> createUserGateway(int userId) {
        try {
            UserGateway<? extends User> userGateway;
            if (isClient(userId)) {
                userGateway = new ClientRepository();
            } else {
                userGateway = new AttorneyRepository();
            }
            return userGateway;
        } catch (ApplicationException e) {
            throw new ApplicationException("UserId does not exist");
        }
    }

    /**
     * Checks if the given user ID belongs to a client.
     *
     * @param userId The ID of the user to check.
     * @return True if the user is a client, false if the user is an attorney.
     * @throws ApplicationException If the user ID format is invalid or if it doesn't exist.
     */
    private static boolean isClient(int userId) throws ApplicationException {
        if (Integer.toString(userId).startsWith("1")) {
            return false;
        } else if (Integer.toString(userId).startsWith("2")) {
            return true;
        } else {
            throw new ApplicationException("UserId does not exist");
        }
    }
}
