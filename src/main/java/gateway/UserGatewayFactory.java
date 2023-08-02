package gateway;

import screen.FailMessage;

public class UserGatewayFactory {

    public UserGateway createUserGateway(int userId) {
        UserGateway userGateway;

        if (isClient(userId)) {
            userGateway = new ClientRepository();
        } else {
            userGateway = new AttorneyRepository();
        }
        return userGateway;
    }
    private static boolean isClient(int userId) throws FailMessage {
        if (Integer.toString(userId).startsWith("1")) {
            return false;
        } else if (Integer.toString(userId).startsWith("2")) {
            return true;
        } else {
            throw new FailMessage("UserId does not exist");
        }
    }
}
