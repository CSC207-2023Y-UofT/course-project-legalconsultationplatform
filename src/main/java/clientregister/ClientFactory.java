package clientregister;
import userentities.Client;

public class ClientFactory {
    public Client create(int userId, String password, String stateAbb,
                         String postalCode, String ethnicity, int age,
                         String gender, String maritalStatus, int numberOfHousehold,
                         float annualIncome){
        return new Client(userId, password, stateAbb, postalCode, ethnicity, age,
                gender, maritalStatus,numberOfHousehold, annualIncome);
    }
}
