package businessrule.gateway;

import driver.database.AttorneyRepository;
import driver.database.ClientRepository;

import driver.screen.ApplicationException;

/**
 * This class represents creating appropriate UserGateway instances based on whether
 * the user is a client or an attorney.
 * It uses the provided user ID to determine the user type and creates a corresponding
 * UserGateway instance accordingly.
 */
public class UserGatewayFactory {

    /**
     * Create a UserGateway instance based on the user ID.
     *
     * @param userId The ID of the user for whom to create the UserGateway instance.
     * @return A UserGateway instance that corresponds to the user type.
     * @throws ApplicationException If the user ID is not valid or if an error occurs during gateway creation.
     */
    public UserGateway createUserGateway(int userId) throws ApplicationException {
        try {
            UserGateway userGateway;
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
     * Check if a user with the provided ID is a client.
     *
     * @param userId The ID of the user to check.
     * @return True if the user is a client, false if the user is an attorney.
     * @throws ApplicationException If the user ID is not valid.
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