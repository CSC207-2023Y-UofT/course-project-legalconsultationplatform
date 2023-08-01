package gateway;

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

    // TODO: implement this method
    private static boolean isClient(int userId) {
        return false;
    }
}
