package entity.factory;
import entity.Client;

/**
 * This is a class representing initialization of a client.
 *
 * This is a factory class responsible for creating instances of the "Client" class.
 * The "ClientFactory" class provides a convenient way to create new instances of the "Client" class
 *
 */
public class ClientFactory {
    public Client create(int userId, String userName, String email, String password, String stateAbb,
                         String postalCode, String ethnicity, int age,
                         String gender, String maritalStatus, int numberOfHousehold,
                         float annualIncome){
        return new Client(userId, userName, email, password, stateAbb, postalCode, ethnicity, age,
                gender, maritalStatus,numberOfHousehold, annualIncome);
    }
}
