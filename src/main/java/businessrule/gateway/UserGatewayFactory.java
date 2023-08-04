package businessrule.gateway;

import driver.database.AttorneyRepository;
import driver.database.ClientRepository;

import driver.screen.ApplicationException;

public class UserGatewayFactory {

    public UserGateway createUserGateway(int userId) {
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
