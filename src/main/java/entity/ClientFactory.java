package entity;
import entity.Client;

public class ClientFactory {
    public Client create(int userId, String userName, String email, String password, String stateAbb,
                         String postalCode, String ethnicity, int age,
                         String gender, String maritalStatus, int numberOfHousehold,
                         float annualIncome){
        return new Client(userId, userName, email, password, stateAbb, postalCode, ethnicity, age,
                gender, maritalStatus,numberOfHousehold, annualIncome);
    }
}
