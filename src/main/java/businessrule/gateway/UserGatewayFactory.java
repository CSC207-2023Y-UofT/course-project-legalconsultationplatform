package businessrule.gateway;

import driver.database.AttorneyRepository;
import driver.database.ClientRepository;
import entity.ApplicationException;
import entity.User;

public class UserGatewayFactory {

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
