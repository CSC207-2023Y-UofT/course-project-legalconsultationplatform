package gateway;

public class UserGatewayFactory {
    final DatabaseConnection databaseConnection;

    public UserGatewayFactory(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public UserGateway createUserGateway(int userId) {
        UserGateway userGateway;

        if (isClient(userId)) {
            userGateway = new ClientRepository(databaseConnection);
        } else {
            userGateway = new AttorneyRepository(databaseConnection);
        }
        return userGateway;
    }

    // TODO: implement this method
    private static boolean isClient(int userId) {
        return false;
    }
}
